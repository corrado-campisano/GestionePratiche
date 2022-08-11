package eu.campesinux.GestionePratiche.avanzamenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

@Controller
public class AvanzamentoController {
	@Autowired
	private AvanzamentoService service;
	@Autowired
	private StatoPraticaService statoService;	
	
	@RequestMapping("/avanzamenti")
	public String home(Model model) {
		List<Avanzamento> listaAvanzamenti = service.listAll();
		model.addAttribute("listaClienti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	@RequestMapping("/avanzamenti/new")
	public String add(Model model) {
		Avanzamento avanzamento = new Avanzamento();
		model.addAttribute("avanzamento", avanzamento);
		
		List<StatoPratica> listaStatiPrecedenti = statoService.listAll();
		model.addAttribute("listaStatiPrecedenti", listaStatiPrecedenti);
		
		List<StatoPratica> listaStatiAttuali = statoService.listAll();
		model.addAttribute("listaStatiAttuali", listaStatiAttuali);
		
		return "avanzamenti/new";
	}
	
	@RequestMapping(value = "/avanzamenti/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("avanzamento") Avanzamento avanzamento) {
		service.save(avanzamento);
		
		return "redirect:/avanzamenti";
	}
	
	@RequestMapping("/avanzamenti/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("avanzamenti/edit");
		
		Avanzamento avanzamento = service.get(id);
		mav.addObject("avanzamento", avanzamento);
		
		return mav;
	}	
	
	@RequestMapping("/avanzamenti/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/avanzamenti";
	}
}
