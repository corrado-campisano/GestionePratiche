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

@Controller
public class SpecializzazioneController {
	@Autowired
	private SpecializzazioneService service;
	
	@RequestMapping("/specializzazioni")
	public String viewHomePage(Model model) {
		List<Specializzazione> listaSpecializzazioni = service.listAll();
		model.addAttribute("listaSpecializzazioni", listaSpecializzazioni);
		
		return "specializzazioni/index";
	}
	
	@RequestMapping("/specializzazioni/new")
	public String showNewProductForm(Model model) {
		Specializzazione specializzazione = new Specializzazione();
		model.addAttribute("specializzazione", specializzazione);
		
		return "specializzazioni/new";
	}
	
	@RequestMapping(value = "/specializzazioni/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("specializzazione") Specializzazione specializzazione) {
		service.save(specializzazione);
		
		return "redirect:/specializzazioni";
	}
	
	@RequestMapping("/specializzazioni/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("specializzazioni/edit");
		
		Specializzazione specializzazione = service.get(id);
		mav.addObject("specializzazione", specializzazione);
		
		return mav;
	}	
	
	@RequestMapping("/specializzazioni/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/specializzazioni";
	}
}
