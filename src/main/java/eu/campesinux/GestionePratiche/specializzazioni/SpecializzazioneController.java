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

import eu.campesinux.GestionePratiche.utenti.Utente;
import eu.campesinux.GestionePratiche.utenti.UtenteService;

@Controller
public class SpecializzazioneController {
	
	@Autowired
	private SpecializzazioneService service;
	
	@Autowired
	private UtenteService utenteService;
	
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
		List<Utente> utenti = utenteService.listBySpecializzazione(specializzazione);
		
		if (utenti.size()>0) {
			String msg = "Errore, impossibile eliminare la specializzazione '" + specializzazione.getDescrizione();
			msg += "', in quanto e' attribuita ai seguenti professionisti: ";
			for (Utente utente : utenti) {
				msg += utente.toString() + " ";
			}
			throw new Exception(msg);
		}
		
		service.delete(id);
		
		return "redirect:/specializzazioni";
	}
}
