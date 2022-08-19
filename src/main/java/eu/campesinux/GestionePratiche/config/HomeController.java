package eu.campesinux.GestionePratiche.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.campesinux.GestionePratiche.quartzJobs.PraticheScadenzeJob;

@Controller
public class HomeController {

	@Autowired
	Scheduler scheduler;
	
	@RequestMapping("/")
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
		
		return "index";
	}
	
}
