package eu.campesinux.GestionePratiche.professionisti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;
import eu.campesinux.GestionePratiche.specializzazioni.SpecializzazioneService;

@Controller
public class ProfessionistaController {
	@Autowired
	private ProfessionistaService service;
	@Autowired
	private SpecializzazioneService specService;
	
	@RequestMapping("/professionisti")
	public String viewHomePage(Model model) {
		List<Professionista> listaProfessionisti = service.listAll();
		model.addAttribute("listaProfessionisti", listaProfessionisti);
		
		return "professionisti/index";
	}
	
	@RequestMapping("/professionisti/new")
	public String showNewProductForm(Model model) {
		
		Professionista professionista = new Professionista();
		model.addAttribute("professionista", professionista);
		
		List<Specializzazione> listaSpecializzazioni = specService.listAll();
		model.addAttribute("listaSpecializzazioni", listaSpecializzazioni);
		
		return "professionisti/new";
	}
	
	@RequestMapping(value = "/professionisti/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("professionista") Professionista professionista, 
			@RequestParam (name="specializzazioni", required=true) Long[] specializzazioni) {
		
		if (specializzazioni!=null) {
			Specializzazione spec = null;
			for (int i = 0; i < specializzazioni.length; i++) {
				Long id = specializzazioni[i];
				spec = specService.get(id);
				professionista.addSpecializzazione(spec);
			}
		}
		
		service.save(professionista);
				
		return "redirect:/professionisti";
	}
	
	@RequestMapping("/professionisti/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("professionisti/edit");

		List<Specializzazione> listaSpecializzazioni = specService.listAll();
		mav.addObject("listaSpecializzazioni", listaSpecializzazioni);
		
		Professionista professionista = service.get(id);
		mav.addObject("professionista", professionista);
		
		return mav;
	}	
	
	@RequestMapping("/professionisti/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/professionisti";
	}
}
