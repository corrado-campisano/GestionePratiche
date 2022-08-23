package eu.campesinux.GestionePratiche.quartzJobs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.campesinux.GestionePratiche.Utils;

@Controller
public class JobController {

	@Autowired
	Scheduler scheduler;
	
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);

	@RequestMapping("/quartzJobs")
	public String viewHomePage(Model model) {

		List<PraticheScadenzeJob> jobs = PraticheScadenzeJob.recuperaAutomatismi(scheduler);

		model.addAttribute("listaJobs", jobs);

		return "quartzJobs/index";
	}

	@RequestMapping("/quartzJobs/new")
	public String add(Model model) {

		return "quartzJobs/new";
	}

	@RequestMapping("/quartzJobs/delete")
	public String delete(Model model, @RequestParam(name = "jobName", required = true) String jobName) {

		JobKey jobKey = new JobKey(jobName, "PraticheScadenzeJob");
		
		try {
			boolean interrupted = scheduler.interrupt(jobKey);
			if (!interrupted) {
				logger.error("Quartz failed to interrupt the job!");
			}
			boolean deleted = scheduler.deleteJob(jobKey);
			if (!deleted) {
				logger.error("Quartz failed to delete the job!");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

		return "redirect:/quartzJobs";
	}

	@RequestMapping("/quartzJobs/save")
	public String save(Model model, @RequestParam(name = "giorniAnticipo", required = true) Long giorniAnticipo) {

		JobDetail jobDetail = buildJobDetail(giorniAnticipo);

		LocalDateTime afterNight = LocalDateTime.now();
		
		afterNight = afterNight.withHour(4);
		afterNight = afterNight.withMinute(0);
		afterNight = afterNight.withSecond(0);
		
		ZonedDateTime zonedDateTime = afterNight.atZone(ZoneId.systemDefault());

		Trigger trigger = buildJobTrigger(jobDetail, zonedDateTime);

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException ex) {
			logger.error(ex.getMessage(), ex);
		}

		return "redirect:/quartzJobs";
	}

	private JobDetail buildJobDetail(Long giorniAnticipo) {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("giorniAnticipo", giorniAnticipo);
		
		return JobBuilder.newJob(PraticheScadenzeJob.class)
				.withIdentity(UUID.randomUUID().toString(), "PraticheScadenzeJob")
				.withDescription("PraticheScadenzeJob").usingJobData(jobDataMap).storeDurably().build();
	}

	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "PraticheScadenzeJob-triggers")
				.withDescription("PraticheScadenzeJob Trigger").startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInHours(24)).build();
	}
}
