package eu.campesinux.GestionePratiche.quartzJobs;

import java.time.LocalDateTime;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import eu.campesinux.GestionePratiche.Utils;
import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.pratiche.PraticaBusinessLogic;
import eu.campesinux.GestionePratiche.pratiche.PraticaService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

public class PraticheScadenzeJob extends QuartzJobBean {

	String jobName;
	String groupName;
	LocalDateTime nextExec;

	@Autowired
	private PraticaService pService;
	@Autowired
	private AvanzamentoService aService;
	@Autowired
	private StatoPraticaService spService;
	
	private final static Logger logger = LoggerFactory.getLogger(PraticheScadenzeJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobDataMap jobDataMap = context.getMergedJobDataMap();
		Long giorniAnticipo = jobDataMap.getLong("giorniAnticipo");
		logger.info("Esecuzione del job con parametro 'giorniAnticipo' = " + giorniAnticipo);

		logger.info("Processamento pratiche candidate ad andare in scadenza");
		List<Pratica> praticheDaMettereInScadenza = pService.listDaMettereInScadenzaPerJob();
		for (Pratica pratica : praticheDaMettereInScadenza) {

			logger.info("Processamento della pratica: " + pratica.getIdentificativo());
			Avanzamento ultimoAvanzamento = PraticaBusinessLogic.getUltimoAvanzamento(pratica, aService);

			logger.info("L'ultimo avanzamento e' in data: " + ultimoAvanzamento.getData());
			logger.info("La relativa data di scadenza e': " + ultimoAvanzamento.getScadenza());

			// pratiche da mettere in scadenza
			// se l'ultima data di scadenza e' prima di oggi + giorniAnticipo
			LocalDateTime oggi = LocalDateTime.now();
			oggi.plusDays(giorniAnticipo);
			if (ultimoAvanzamento.getScadenza().isBefore(oggi.plusDays(giorniAnticipo))) {

				logger.info("la pratica verra' messa in scadenza");
				
				// crea un nuovo avanzamento
				Avanzamento inScadenza = new Avanzamento();
				inScadenza.setPratica(pratica);
				inScadenza.setDescrizione("Pratica messa 'in scadenza' in automatico");
				inScadenza.setData(oggi);
				inScadenza.setScadenza(ultimoAvanzamento.getScadenza());
				StatoPratica statoAttuale = spService.findByStato(StatoPratica.STATO_IN_SCADENZA);
				inScadenza.setStatoAttuale(statoAttuale);
				inScadenza.setStatoPrecedente(ultimoAvanzamento.getStatoAttuale());
				aService.save(inScadenza);
				// aggiorna lo stato della pratica
				pratica.setStato(statoAttuale);
				pService.save(pratica);

			}
		}

		logger.info("Processamento pratiche candidate a diventare scadute");
		List<Pratica> praticheDaMettereScadute = pService.listDaMettereScadutePerJob();
		for (Pratica pratica : praticheDaMettereScadute) {

			logger.info("Processamento della pratica: " + pratica.getIdentificativo());
			Avanzamento ultimoAvanzamento = PraticaBusinessLogic.getUltimoAvanzamento(pratica, aService);

			logger.info("L'ultimo avanzamento e' in data: " + ultimoAvanzamento.getData());
			logger.info("La relativa data di scadenza e': " + ultimoAvanzamento.getScadenza());

			// pratiche da mettere in scadute
			// se l'ultima data di scadenza e' prima di oggi
			LocalDateTime oggi = LocalDateTime.now();
			if (ultimoAvanzamento.getScadenza().isBefore(oggi)) {

				logger.info("la pratica verra' considerata scaduta");
				
				// crea un nuovo avanzamento
				Avanzamento inScadenza = new Avanzamento();
				inScadenza.setPratica(pratica);
				inScadenza.setDescrizione("Pratica divenuta 'scaduta' in automatico");
				inScadenza.setData(oggi);
				inScadenza.setScadenza(ultimoAvanzamento.getScadenza());
				StatoPratica statoAttuale = spService.findByStato(StatoPratica.STATO_SCADUTA);
				inScadenza.setStatoAttuale(statoAttuale);
				inScadenza.setStatoPrecedente(ultimoAvanzamento.getStatoAttuale());
				aService.save(inScadenza);
				// aggiorna lo stato della pratica
				pratica.setStato(statoAttuale);
				pService.save(pratica);
			}
		}

	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public LocalDateTime getNextExec() {
		return nextExec;
	}

	public void setNextExec(LocalDateTime nextExec) {
		this.nextExec = nextExec;
	}

}
