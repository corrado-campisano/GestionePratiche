package eu.campesinux.GestionePratiche.quartzJobs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {

	@Autowired
	Scheduler scheduler;

	@RequestMapping("/quartzJobs")
	public String viewHomePage(Model model) {

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
					  Date nextFireTime = null;
					  if (!trigs.isEmpty()) {
						  nextFireTime = trigs.get(0).getNextFireTime();
					  }
					  psj.setNextExec(nextFireTime);
					  
					  jobs.add(psj);
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		model.addAttribute("listaJobs", jobs);
		
		return "quartzJobs/index";
	}
	
	@RequestMapping("/quartzJobs/new")
	public String add(Model model) {
		
		return "quartzJobs/new";
	}

	@RequestMapping("/quartzJobs/delete")
	public String delete(Model model,
			@RequestParam(name = "jobName", required = true) String jobName) {

		// TODO : deleting does not work
		
		JobKey jobKey = new JobKey(jobName);
		try {
			boolean interrupted = scheduler.interrupt(jobKey);
			if (!interrupted) {
				System.out.println("Quartz failed to interrupt the job!");
            }
			boolean deleted = scheduler.deleteJob(jobKey);
			if (!deleted) {
				System.out.println("Quartz failed to delete the job!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
		
		return "redirect:/quartzJobs";
	}
	
	@RequestMapping("/quartzJobs/save")
	public String save(Model model,
			@RequestParam(name = "giorniAnticipo", required = true) Long giorniAnticipo) {
		
		JobDetail jobDetail = buildJobDetail(giorniAnticipo);
		
		LocalDateTime afterNight = LocalDateTime.now();
		afterNight = afterNight.plusMinutes(2);
//		afterNight = afterNight.withHour(3);
//		afterNight = afterNight.withMinute(0);
//		afterNight = afterNight.withSecond(0);
		ZonedDateTime zonedDateTime = afterNight.atZone(ZoneId.systemDefault());
		
		Trigger trigger = buildJobTrigger(jobDetail, zonedDateTime);
        
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/quartzJobs";
	}
	
	private JobDetail buildJobDetail(Long giorniAnticipo) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("giorniAnticipo", giorniAnticipo);

        return JobBuilder.newJob(PraticheScadenzeJob.class)
                .withIdentity(UUID.randomUUID().toString(), "PraticheScaduteJob")
                .withDescription("PraticheScaduteJob")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "PraticheScaduteJob-triggers")
                .withDescription("PraticheScaduteJob Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInHours(24))
                .build();
    }
}
