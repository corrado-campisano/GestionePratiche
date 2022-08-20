package eu.campesinux.GestionePratiche.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

import eu.campesinux.GestionePratiche.quartzJobs.PraticheScadenzeJob;

public class Utils {

	public static List<PraticheScadenzeJob> recuperaAutomatismi(Scheduler scheduler) {
		
		List<PraticheScadenzeJob> jobs = new ArrayList<PraticheScadenzeJob>();

		try {
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					PraticheScadenzeJob psj = new PraticheScadenzeJob();
					psj.setGroupName(jobGroup);
					psj.setJobName(jobName);

					// get job's trigger
					List<? extends Trigger> trigs = scheduler.getTriggersOfJob(jobKey);
					LocalDateTime nextFireTime = null;
					if (!trigs.isEmpty()) {
						Date nextFireTimeDte = trigs.get(0).getNextFireTime();
						nextFireTime = Utils.convertToLocalDateTimeViaMilisecond(nextFireTimeDte);
					}
					psj.setNextExec(nextFireTime);

					jobs.add(psj);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobs;
	}
	
	
	
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}

	public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}
	
	public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
