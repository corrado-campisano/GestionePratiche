package eu.campesinux.GestionePratiche.avanzamenti;

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

import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.pratiche.PraticaBusinessLogic;
import eu.campesinux.GestionePratiche.pratiche.PraticaService;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

@Controller
public class AvanzamentoController {
	@Autowired
	private AvanzamentoService avanzamentoService;
	@Autowired
	private StatoPraticaService statoService;	
	@Autowired
	private PraticaService praticaService;
	
	@RequestMapping("/avanzamenti")
	public String home(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	//presaInCarico
	@RequestMapping("/avanzamenti/presaInCarico")
	public String presaInCarico(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.presaInCarico(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	//prontoPerNotifica
	@RequestMapping("/avanzamenti/prontoPerNotifica")
	public String prontoPerNotifica(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.prontoPerNotifica(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	//prontoPerDeposito
	@RequestMapping("/avanzamenti/prontoPerDeposito")
	public String prontoPerDeposito(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.prontoPerDeposito(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	// inDibattimento
	@RequestMapping("/avanzamenti/inDibattimento")
	public String inDibattimento(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.inDibattimento(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	// daFatturare
	@RequestMapping("/avanzamenti/daFatturare")
	public String daFatturare(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.daFatturare(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	// praticaEvasa
	@RequestMapping("/avanzamenti/praticaEvasa")
	public String praticaEvasa(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.praticaEvasa(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	//praticaRigettata
	@RequestMapping("/avanzamenti/praticaRigettata")
	public String praticaRigettata(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "commento", required = true) String commento) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		PraticaBusinessLogic.praticaRigettata(pratica, praticaService, statoService, avanzamentoService, commento);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	//praticaInScadenza
	//praticaScaduta
	
	
	@RequestMapping("/avanzamenti/new")
	public String add(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id) {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		Avanzamento avanzamento = new Avanzamento();
		model.addAttribute("avanzamento", avanzamento);
		
		List<StatoPratica> listaStatiPrecedenti = statoService.listAll();
		model.addAttribute("listaStatiPrecedenti", listaStatiPrecedenti);
		
		List<StatoPratica> listaStatiAttuali = statoService.listAll();
		model.addAttribute("listaStatiAttuali", listaStatiAttuali);
		
		return "avanzamenti/new";
	}
	
	@RequestMapping(value = "/avanzamenti/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("avanzamento") Avanzamento avanzamento, 
			@RequestParam(name = "pratica_id", required = true) Long pratica_id) {
		
		Pratica pratica = praticaService.get(pratica_id);
		pratica.setStato(avanzamento.getStatoAttuale());
		praticaService.save(pratica);
		
		avanzamento.setPratica(pratica);
		avanzamentoService.save(avanzamento);
		
		return "redirect:/avanzamenti/?pratica=" + pratica.getId();
	}
	
	@RequestMapping("/avanzamenti/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("avanzamenti/edit");
		
		Avanzamento avanzamento = avanzamentoService.get(id);
		mav.addObject("avanzamento", avanzamento);
		
		Pratica pratica = avanzamento.getPratica();
		mav.addObject("pratica", pratica);
		
		List<StatoPratica> listaStatiPrecedenti = statoService.listAll();
		mav.addObject("listaStatiPrecedenti", listaStatiPrecedenti);
		
		List<StatoPratica> listaStatiAttuali = statoService.listAll();
		mav.addObject("listaStatiAttuali", listaStatiAttuali);
		
		return mav;
	}	
	
	@RequestMapping("/avanzamenti/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		
		Avanzamento avanzamento = avanzamentoService.get(id);
		Pratica pratica = avanzamento.getPratica();
		
		avanzamentoService.delete(id);
		
		return "redirect:/avanzamenti/?pratica=" + pratica.getId();
	}
}
