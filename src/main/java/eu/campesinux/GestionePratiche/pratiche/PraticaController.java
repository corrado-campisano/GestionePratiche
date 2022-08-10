package eu.campesinux.GestionePratiche.pratiche;

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

import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.clienti.ClienteService;
import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.professionisti.ProfessionistaService;
import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

@Controller
public class PraticaController {
	@Autowired
	private PraticaService service;
	@Autowired
	private ProfessionistaService profService;
	@Autowired
	private ClienteService clienteService;
	
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
		
		List<Professionista> listaProfessionisti = profService.listAll();
		model.addAttribute("listaProfessionisti", listaProfessionisti);

		List<Cliente> listaClienti = clienteService.listAll();
		model.addAttribute("listaClienti", listaClienti);
		
		return "pratiche/new";
	}
	
	@RequestMapping(value = "/pratiche/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("pratica") Pratica pratica, 
			@RequestParam (name="professionisti", required=false) Long[] professionisti) {
		
		if (professionisti!=null) {
			Professionista prof = null;
			for (int i = 0; i < professionisti.length; i++) {
				Long id = professionisti[i];
				prof = profService.get(id);
				pratica.addProfessionista(prof);
			}
		}
		
		service.save(pratica);
		
		return "redirect:/pratiche";
	}
	
	@RequestMapping("/pratiche/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("pratiche/edit");
		
		List<Professionista> listaProfessionisti = profService.listAll();
		mav.addObject("listaProfessionisti", listaProfessionisti);
		
		List<Cliente> listaClienti = clienteService.listAll();
		mav.addObject("listaClienti", listaClienti);
		
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
