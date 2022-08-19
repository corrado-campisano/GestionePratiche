package eu.campesinux.GestionePratiche.quartzJobs;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.pratiche.PraticaService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

public class PraticheScadenzeJob extends QuartzJobBean {

	String jobName;
	String groupName;
	Date nextExec;
	
	@Autowired
	private PraticaService pService;
	@Autowired
	private AvanzamentoService aService;
	@Autowired
	private StatoPraticaService spService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobDataMap jobDataMap = context.getMergedJobDataMap();
		Long giorniAnticipo = jobDataMap.getLong("giorniAnticipo");
		System.out.println("Esecuzione del job con parametro 'giorniAnticipo' = " + giorniAnticipo);
		
		List<Pratica> praticheAperte = pService.listAperte();
		for (Pratica pratica : praticheAperte) {
			System.out.println("Processamento della pratica: " + pratica.getIdentificativo());
			List<Avanzamento> avanzamenti = aService.listByPratica(pratica);
			LocalDateTime dataAvanzamentoMax=null;
			LocalDateTime dataScadenzaMax=null;
			StatoPratica statoPrecedente=null;
			for (Avanzamento avanzamento : avanzamenti) {
				if (dataAvanzamentoMax==null) {
					dataAvanzamentoMax = avanzamento.getData(); 
					dataScadenzaMax = avanzamento.getScadenza();
					statoPrecedente = avanzamento.getStatoAttuale();
				} else {
					if (avanzamento.getData().isAfter(dataAvanzamentoMax)) {
						dataAvanzamentoMax = avanzamento.getData(); 
						dataScadenzaMax = avanzamento.getScadenza();
						statoPrecedente = avanzamento.getStatoAttuale();
					}					
				}
			}
			System.out.println("L'ultimo avanzamento e' in data: " + dataAvanzamentoMax);
			System.out.println("La relativa data di scadenza e': " + dataScadenzaMax);
			
			// pratiche da mettere in scadenza
			// se l'ultima data di scadenza e' prima di oggi + giorniAnticipo
			LocalDateTime oggi = LocalDateTime.now();
			oggi.plusDays(giorniAnticipo);
			if(dataScadenzaMax.isBefore(oggi.plusDays(giorniAnticipo))) {
				
				System.out.println("la pratica verra' messa in scadenza");
				
				// crea un nuovo avanzamento 
				Avanzamento inScadenza = new Avanzamento();
				inScadenza.setPratica(pratica);
				inScadenza.setDescrizione("Pratica messa 'in scadenza' in automatico");
				inScadenza.setData(oggi);
				inScadenza.setScadenza(dataScadenzaMax);
				StatoPratica statoAttuale = spService.findByStato(StatoPratica.STATO_IN_SCADENZA);
				inScadenza.setStatoAttuale(statoAttuale);
				inScadenza.setStatoPrecedente(statoPrecedente);
				aService.save(inScadenza);
				// aggiorna lo stato della pratica
				pratica.setStato(statoAttuale);
				pService.save(pratica);
				
			}
			// pratiche da mettere in scadute
			// se l'ultima data di scadenza e' prima di oggi
			if(dataScadenzaMax.isBefore(oggi)) {
				
				System.out.println("la pratica verra' considerata scaduta");
				
				// crea un nuovo avanzamento 
				Avanzamento inScadenza = new Avanzamento();
				inScadenza.setPratica(pratica);
				inScadenza.setDescrizione("Pratica divenuta 'scaduta' in automatico");
				inScadenza.setData(oggi);
				inScadenza.setScadenza(dataScadenzaMax);
				StatoPratica statoAttuale = spService.findByStato(StatoPratica.STATO_SCADUTA);
				inScadenza.setStatoAttuale(statoAttuale);
				inScadenza.setStatoPrecedente(statoPrecedente);
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

	public Date getNextExec() {
		return nextExec;
	}

	public void setNextExec(Date nextExec) {
		this.nextExec = nextExec;
	}
	
}
