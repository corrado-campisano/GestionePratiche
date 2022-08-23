package eu.campesinux.GestionePratiche.utenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.pratiche.PraticaService;
import eu.campesinux.GestionePratiche.ruoli.Ruolo;
import eu.campesinux.GestionePratiche.ruoli.RuoloService;
import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;
import eu.campesinux.GestionePratiche.specializzazioni.SpecializzazioneService;

@Controller
public class UtenteController {
	@Autowired
	private UtenteService service;
	@Autowired
	private SpecializzazioneService specService;
	@Autowired
	private PraticaService praticheService;
	@Autowired
	private RuoloService ruoloService;
	
	@RequestMapping("/utenti")
	public String home(Model model) {
		List<Utente> listaUtenti = service.listAll();
		model.addAttribute("listaUtenti", listaUtenti);
		
		return "utenti/index";
	}
	
	@RequestMapping("/utenti/new")
	public String add(Model model) {
		
		Utente utente = new Utente();
		model.addAttribute("utente", utente);

		List<Ruolo> listaRuoli = ruoloService.listAll();
		model.addAttribute("listaRuoli", listaRuoli);
		
		List<Specializzazione> listaSpecializzazioni = specService.listAll();
		model.addAttribute("listaSpecializzazioni", listaSpecializzazioni);
		
		return "utenti/new";
	}
	
	@RequestMapping(value = "/utenti/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("utente") Utente utente, 
			@RequestParam (name="password", required=true) String password,
			@RequestParam (name="specializzazioni", required=false) Long[] specializzazioni) {
		
		if (specializzazioni!=null) {
			Specializzazione spec = null;
			for (int i = 0; i < specializzazioni.length; i++) {
				Long id = specializzazioni[i];
				spec = specService.get(id);
				utente.addSpecializzazione(spec);
			}
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		utente.setPassword(encoder.encode(password));
		
		service.save(utente);
				
		return "redirect:/utenti";
	}
	
	@RequestMapping("/utenti/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("utenti/edit");

		List<Ruolo> listaRuoli = ruoloService.listAll();
		mav.addObject("listaRuoli", listaRuoli);
		
		List<Specializzazione> listaSpecializzazioni = specService.listAll();
		mav.addObject("listaSpecializzazioni", listaSpecializzazioni);
		
		Utente utente = service.get(id);
		mav.addObject("utente", utente);
		
		return mav;
	}	
	
	@RequestMapping("/utenti/delete/{id}")
	public String delete(@PathVariable(name = "id") Long id) throws Exception {
				
		Utente utente = service.get(id);
				
		List<Specializzazione> specializzazioni = specService.listByUtente(utente);		
		if (specializzazioni.size()>0) {
			String msg = "Errore, impossibile eliminare l'utente '" + utente.toString();
			msg += "', in quanto gli sono attribuite le seguenti specializzazioni: ";
			for (Specializzazione spec : specializzazioni) {
				msg += spec.getDescrizione() + " ";
			}
			throw new Exception(msg);
		}
		
		List<Pratica> pratiche = praticheService.listByUtente(utente); 
		if (pratiche.size()>0) {
			String msg = "Errore, impossibile eliminare il professionista '" + utente.toString();
			msg += "', in quanto gli sono state assegnate le seguenti pratiche: ";
			for (Pratica pratica : pratiche) {
				msg += "(" + pratica.getId() + ") " + pratica.getDescrizione() + " ";
			}
			throw new Exception(msg);
		}
		
		service.delete(id);
		
		return "redirect:/utenti";
	}
}
