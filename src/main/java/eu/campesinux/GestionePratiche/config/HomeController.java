package eu.campesinux.GestionePratiche.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
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

		List<PraticheScadenzeJob> jobs = Utils.recuperaAutomatismi(scheduler);

		model.addAttribute("listaJobs", jobs);

		return "index";
	}

}
