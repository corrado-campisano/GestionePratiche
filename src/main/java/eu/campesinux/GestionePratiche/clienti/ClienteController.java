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

import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.pratiche.PraticaService;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService service;
	@Autowired
	private PraticaService praticaService;
	
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
	public String save(@ModelAttribute("cliente") Cliente cliente) throws Exception {
		
		if (cliente.getId()==null) {
			Cliente omocode = service.getByCodiceFiscale(cliente.getCodiceFiscale());
			if (omocode!=null) {
				throw new Exception("Un cliente con il codice fiscale '" + omocode.getCodiceFiscale() + 
						"' esiste gia', specificarne uno diverso");
			}
		}
		
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
	public String delete(@PathVariable(name = "id") Long id) throws Exception {
		
		Cliente cliente = service.get(id);
		
		List<Pratica> pratiche = praticaService.listByCliente(cliente); 
		if (pratiche.size()>0) {
			String msg = "Errore, impossibile eliminare il cliente '" + cliente.toString();
			msg += "', in quanto gli sono state assegnate le seguenti pratiche: ";
			for (Pratica pratica : pratiche) {
				msg += "(" + pratica.getId() + ") " + pratica.getDescrizione() + " ";
			}
			throw new Exception(msg);
		}
		
		service.delete(id);
		
		return "redirect:/clienti";
	}
}
