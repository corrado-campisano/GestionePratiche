package eu.campesinux.GestionePratiche.clienti;

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
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@RequestMapping("/clienti")
	public String home(Model model) {
		List<Cliente> listaClienti = service.listAll();
		model.addAttribute("listaClienti", listaClienti);
		
		return "clienti/index";
	}
	
	@RequestMapping("/clienti/new")
	public String add(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		
		return "clienti/new";
	}
	
	@RequestMapping(value = "/clienti/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("cliente") Cliente cliente) {
		service.save(cliente);
		
		return "redirect:/clienti";
	}
	
	@RequestMapping("/clienti/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("clienti/edit");
		
		Cliente cliente = service.get(id);
		mav.addObject("cliente", cliente);
		
		return mav;
	}	
	
	@RequestMapping("/clienti/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/clienti";
	}
}
