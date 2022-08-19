package eu.campesinux.GestionePratiche.quartzJobs;

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
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

public class PraticheScadenzeJob extends QuartzJobBean {

	String jobName;
	String groupName;
	Date nextExec;
	
	@Autowired
	private PraticaService pService;
	@Autowired
	private AvanzamentoService aService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobDataMap jobDataMap = context.getMergedJobDataMap();
		Long giorniAnticipo = jobDataMap.getLong("giorniAnticipo");
		System.out.println("Esecuzione del job con parametro giorniAnticipo = " + giorniAnticipo);
		
		List<Pratica> praticheAperte = pService.listAperte();
		for (Pratica pratica : praticheAperte) {
			System.out.println("Processamento della pratica" + pratica.getIdentificativo());
			List<Avanzamento> avanzamenti = aService.listByPratica(pratica);
			Date dataAvanzamentoMax=null;
			Date dataScadenzaMax=null;
			for (Avanzamento avanzamento : avanzamenti) {
				if (dataAvanzamentoMax==null) {
					dataAvanzamentoMax = avanzamento.getData(); 
					dataScadenzaMax = avanzamento.getScadenza();
				} else {
					if (avanzamento.getData().after(dataAvanzamentoMax)) {
						dataAvanzamentoMax = avanzamento.getData(); 
						dataScadenzaMax = avanzamento.getScadenza();
					}					
				}
			}
			System.out.println("L'ultimo avanzamento e' in data" + dataAvanzamentoMax);
			System.out.println("La relativa data di scadenza e'" + dataScadenzaMax);
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
