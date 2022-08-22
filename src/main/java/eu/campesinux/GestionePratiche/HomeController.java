package eu.campesinux.GestionePratiche;

import java.util.List;

import org.quartz.Scheduler;
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
