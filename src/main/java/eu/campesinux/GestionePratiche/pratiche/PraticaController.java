package eu.campesinux.GestionePratiche.pratiche;

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
public class PraticaController {
	@Autowired
	private PraticaService service;
	
	@RequestMapping("/pratiche")
	public String viewHomePage(Model model) {
		List<Pratica> listaPratiche = service.listAll();
		model.addAttribute("listaPratiche", listaPratiche);
		
		return "pratiche/index";
	}
	
	@RequestMapping("/pratiche/new")
	public String showNewProductForm(Model model) {
		Pratica pratica = new Pratica();
		model.addAttribute("pratica", pratica);
		
		return "pratiche/new";
	}
	
	@RequestMapping(value = "/pratiche/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("pratica") Pratica pratica) {
		service.save(pratica);
		
		return "redirect:/pratiche";
	}
	
	@RequestMapping("/pratiche/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("pratiche/edit");
		
		Pratica pratica = service.get(id);
		mav.addObject("pratica", pratica);
		
		return mav;
	}	
	
	@RequestMapping("/pratiche/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/pratiche";
	}
}
