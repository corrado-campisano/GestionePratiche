package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoService;
import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.clienti.ClienteService;
import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.professionisti.ProfessionistaService;
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
	@Autowired
	private AvanzamentoService avanzamentoService;

	int itemsPerPage = 2;

	@RequestMapping("/pratiche")
	public String home(Model model, @RequestParam(name = "filtro", required = false) String filtro,
			@RequestParam(name = "page", required = false) Integer page) {

		int startFrom = 0;
		if (page != null) {
			startFrom = page.intValue();
		}
		model.addAttribute("startFrom", startFrom);
		Pageable pageRequest = PageRequest.of(startFrom, itemsPerPage);

		if (filtro == null) {
			Page<Pratica> listaPratiche = service.listAll(pageRequest);
			model.addAttribute("listaPratiche", listaPratiche);
		} else {
			if (filtro.equals("inScadenza")) {
				Page<Pratica> listaPratiche = service.listInScadenza(statoService);
				model.addAttribute("listaPratiche", listaPratiche);
			}
			if (filtro.equals("daFatturare")) {
				Page<Pratica> listaPratiche = service.listDaFatturare(statoService);
				model.addAttribute("listaPratiche", listaPratiche);
			}
		}
		return "pratiche/index";
	}

	@RequestMapping("/pratiche/new")
	public String add(Model model) {
		Pratica pratica = new Pratica();
		model.addAttribute("pratica", pratica);

		List<Cliente> listaClienti = clienteService.listAll();
		model.addAttribute("listaClienti", listaClienti);

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
	public String delete(@PathVariable(name = "id") Long id) throws Exception {

		Pratica pratica = service.get(id);

		List<Avanzamento> avanzamenti = avanzamentoService.listByPratica(pratica);
		if (avanzamenti.size() > 0) {
			String msg = "Errore, impossibile eliminare la pratica (" + pratica.getId() + ") "
					+ pratica.getDescrizione();
			msg += ", in quanto le sono stati assegnati i seguenti avanzamenti: ";
			for (Avanzamento avanz : avanzamenti) {
				msg += "(" + avanz.getId() + ") " + avanz.getDescrizione() + " ";
			}
			throw new Exception(msg);
		}

		service.delete(id);

		return "redirect:/pratiche";
	}
}
