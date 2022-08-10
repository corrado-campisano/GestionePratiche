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
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;
import eu.campesinux.GestionePratiche.tipoPratica.TipoPratica;
import eu.campesinux.GestionePratiche.tipoPratica.TipoPraticaService;

@Controller
public class PraticaController {
	@Autowired
	private PraticaService service;
	@Autowired
	private ProfessionistaService profService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TipoPraticaService tipoService;
	@Autowired
	private StatoPraticaService statoService;

	@RequestMapping("/pratiche")
	public String home(Model model) {
		List<Pratica> listaPratiche = service.listAll();
		model.addAttribute("listaPratiche", listaPratiche);

		return "pratiche/index";
	}

	@RequestMapping("/pratiche/new")
	public String add(Model model) {
		Pratica pratica = new Pratica();
		model.addAttribute("pratica", pratica);

		List<Professionista> listaProfessionisti = profService.listAll();
		model.addAttribute("listaProfessionisti", listaProfessionisti);

		List<Cliente> listaClienti = clienteService.listAll();
		model.addAttribute("listaClienti", listaClienti);

		List<StatoPratica> listaStati = statoService.listAll();
		model.addAttribute("listaStati", listaStati);
		
		List<TipoPratica> listaTipi = tipoService.listAll();
		model.addAttribute("listaTipi", listaTipi);
		
		return "pratiche/new";
	}

	@RequestMapping(value = "/pratiche/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("pratica") Pratica pratica,
			@RequestParam(name = "professionisti", required = false) Long[] professionisti) {

		if (professionisti != null) {
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
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("pratiche/edit");

		List<Professionista> listaProfessionisti = profService.listAll();
		mav.addObject("listaProfessionisti", listaProfessionisti);

		List<Cliente> listaClienti = clienteService.listAll();
		mav.addObject("listaClienti", listaClienti);
		
		List<StatoPratica> listaStati = statoService.listAll();
		mav.addObject("listaStati", listaStati);
		
		List<TipoPratica> listaTipi = tipoService.listAll();
		mav.addObject("listaTipi", listaTipi);
		
		Pratica pratica = service.get(id);
		mav.addObject("pratica", pratica);

		return mav;
	}

	@RequestMapping("/pratiche/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		service.delete(id);

		return "redirect:/pratiche";
	}
}
