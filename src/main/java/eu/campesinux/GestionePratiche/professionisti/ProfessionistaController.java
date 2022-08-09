package eu.campesinux.GestionePratiche.professionisti;

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
public class ProfessionistaController {
	@Autowired
	private ProfessionistaService service;
	
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
		
		return "professionisti/new";
	}
	
	@RequestMapping(value = "/professionisti/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("professionista") Professionista cliente) {
		service.save(cliente);
		
		return "redirect:/professionisti";
	}
	
	@RequestMapping("/professionisti/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("professionisti/edit");
		
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
