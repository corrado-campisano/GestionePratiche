package eu.campesinux.GestionePratiche.avanzamenti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import eu.campesinux.GestionePratiche.utenti.Utente;
import eu.campesinux.GestionePratiche.utenti.UtenteService;

@Controller
public class AvanzamentoController {
	@Autowired
	private AvanzamentoService avanzamentoService;
	@Autowired
	private StatoPraticaService statoService;	
	@Autowired
	private PraticaService praticaService;
	@Autowired
	private UtenteService utenteService;
	
	@RequestMapping("/avanzamenti")
	public String home(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id) {
		
		List<Utente> listaUtenti = utenteService.listAllProfessionals();
		model.addAttribute("listaUtenti", listaUtenti);
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/index";
	}
	
	@RequestMapping("/avanzamenti/dettaglio")
	public String dettaglio(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id) {
		
		List<Utente> listaUtenti = utenteService.listAllProfessionals();
		model.addAttribute("listaUtenti", listaUtenti);
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "avanzamenti/dettaglio";
	}
	
	//gestione
	@RequestMapping("/avanzamenti/gestione")
	public String gestione(Model model,
			@RequestParam(name = "pratica", required = true) Long pratica_id,
			@RequestParam(name = "azione", required = true) String azione,
			@RequestParam(name = "commento", required = true) String commento,
			@RequestParam(name = "scadenza", required = false) String scadenzaParam,
			@RequestParam(name = "utenti", required = false) Long[] utenti) throws Exception {
		
		Pratica pratica = praticaService.get(pratica_id);
		model.addAttribute("pratica", pratica);
		
		LocalDateTime scadenza = null;
		if (scadenzaParam!=null && !scadenzaParam.equals("")) {
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				scadenza = LocalDateTime.parse(scadenzaParam, dtf);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Specificare una data nel formato yyyy-MM-dd HH:mm:ss");
			}
		}
		
		switch (azione) {
		case "presaInCarico":
			PraticaBusinessLogic.presaInCarico(pratica, utenti, commento, scadenza, 
					utenteService, avanzamentoService, praticaService, statoService);
			break;

		case "prontoPerNotifica":
			PraticaBusinessLogic.prontoPerNotifica(pratica, commento, scadenza,
					statoService, avanzamentoService, praticaService, utenteService);
			break;
			
		case "prontoPerDeposito":
			PraticaBusinessLogic.prontoPerDeposito(pratica, commento, scadenza,
					statoService, avanzamentoService, praticaService, utenteService);
			break;
			
		case "inDibattimento":
			PraticaBusinessLogic.inDibattimento(pratica, commento, scadenza,
					statoService, avanzamentoService, praticaService, utenteService);
			break;

		case "daFatturare":
			PraticaBusinessLogic.daFatturare(pratica, commento, scadenza,
					statoService, avanzamentoService, praticaService, utenteService);
			break;
			
		case "praticaEvasa":
			PraticaBusinessLogic.praticaEvasa(pratica, commento, 
					statoService, avanzamentoService, praticaService);
			break;

		case "praticaRigettata":
			PraticaBusinessLogic.praticaRigettata(pratica, commento,
					statoService, avanzamentoService, praticaService, utenteService);
			break;
			
		case "prorogaPratica":
			PraticaBusinessLogic.prorogaPratica(pratica, commento, 
					avanzamentoService, praticaService);
			break;
			
		default:
			break;
		}
		
		String stato = pratica.getStato().getStato();
		model.addAttribute("stato", stato);
		
		List<Avanzamento> listaAvanzamenti = avanzamentoService.listByPraticaId(pratica_id);
		model.addAttribute("listaAvanzamenti", listaAvanzamenti);
		
		return "redirect:/avanzamenti/?pratica=" + pratica.getId();
	}
		
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
