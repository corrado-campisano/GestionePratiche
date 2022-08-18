package eu.campesinux.GestionePratiche.specializzazioni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.professionisti.ProfessionistaService;

@Controller
public class SpecializzazioneController {
	
	@Autowired
	private SpecializzazioneService service;
	
	@Autowired
	private ProfessionistaService profService;
	
	@RequestMapping("/specializzazioni")
	public String home(Model model) {
		List<Specializzazione> listaSpecializzazioni = service.listAll();
		model.addAttribute("listaSpecializzazioni", listaSpecializzazioni);
		
		return "specializzazioni/index";
	}
	
	@RequestMapping("/specializzazioni/new")
	public String add(Model model) {
		Specializzazione specializzazione = new Specializzazione();
		model.addAttribute("specializzazione", specializzazione);
		
		return "specializzazioni/new";
	}
	
	@RequestMapping(value = "/specializzazioni/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("specializzazione") Specializzazione specializzazione) {
		service.save(specializzazione);
		
		return "redirect:/specializzazioni";
	}
	
	@RequestMapping("/specializzazioni/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("specializzazioni/edit");
		
		Specializzazione specializzazione = service.get(id);
		mav.addObject("specializzazione", specializzazione);
		
		return mav;
	}	
	
	@RequestMapping("/specializzazioni/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) throws Exception {
		
		Specializzazione specializzazione = service.get(id);
		List<Professionista> professionisti = profService.listBySpecializzazione(specializzazione);
		
		if (professionisti.size()>0) {
			String msg = "Errore, impossibile eliminare la specializzazione '" + specializzazione.getDescrizione();
			msg += "', in quanto e' attribuita ai seguenti professionisti: ";
			for (Professionista prof : professionisti) {
				msg += prof.toString() + " ";
			}
			throw new Exception(msg);
		}
		
		service.delete(id);
		
		return "redirect:/specializzazioni";
	}
}
