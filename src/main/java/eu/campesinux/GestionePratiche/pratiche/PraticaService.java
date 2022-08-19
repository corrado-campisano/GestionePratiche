package eu.campesinux.GestionePratiche.pratiche;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoRepository;
import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

@Service
public class PraticaService {
	@Autowired
	private PraticaRepository repo;
	@Autowired
	private AvanzamentoRepository avaRepo;
	@Autowired
	private StatoPraticaService statoService;
	
	public Page<Pratica> listAll(Pageable pageRequest) {
		return repo.findAll(pageRequest);
	}
	
	public void save(Pratica pratica) {
		repo.save(pratica);
	}
	
	public Pratica get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		
		List<Avanzamento> avanzamenti = avaRepo.findByPraticaId(id);
		for(Avanzamento avanzamento : avanzamenti) {
			avaRepo.deleteById(avanzamento.getId());
		}
		
		repo.deleteById(id);
	}

	public Page<Pratica> listInScadenza() {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica inScadenza = statoService.findByStato(StatoPratica.STATO_IN_SCADENZA);
		return repo.findByStato(inScadenza, pageRequest);
	}

	public Page<Pratica> listDaFatturare() {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica daFatturare = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		return repo.findByStato(daFatturare, pageRequest);
	}

	public List<Pratica> listByProfessionisti(Professionista prof) {
		return repo.findByProfessionisti(prof);
	}

	public List<Pratica> listByCliente(Cliente cliente) {
		return repo.findByCliente(cliente);
	}
	
	public List<Pratica> listAperte() {
		List<Pratica> praticheAperte = new ArrayList<>();
		
		StatoPratica stato = statoService.findByStato(StatoPratica.STATO_IN_LAVORAZIONE);
		List<Pratica> pratiche = repo.findByStato(stato);
		for (Pratica pratica : pratiche) praticheAperte.add(pratica);

		stato = statoService.findByStato(StatoPratica.STATO_DA_NOTIFICARE);
		pratiche = repo.findByStato(stato);
		for (Pratica pratica : pratiche) praticheAperte.add(pratica);
		
		stato = statoService.findByStato(StatoPratica.STATO_DA_DEPOSITARE);
		pratiche = repo.findByStato(stato);
		for (Pratica pratica : pratiche) praticheAperte.add(pratica);
		
		stato = statoService.findByStato(StatoPratica.STATO_IN_DIBATTIMENTO);
		pratiche = repo.findByStato(stato);
		for (Pratica pratica : pratiche) praticheAperte.add(pratica);

		stato = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		pratiche = repo.findByStato(stato);
		for (Pratica pratica : pratiche) praticheAperte.add(pratica);
		
		return praticheAperte;
	}
	
	public Page<Pratica> listNuove() {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica nuova = statoService.findByStato(StatoPratica.STATO_NUOVA);
		return repo.findByStato(nuova, pageRequest);
	}
}
