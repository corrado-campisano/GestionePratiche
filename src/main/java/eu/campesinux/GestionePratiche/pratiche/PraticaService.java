package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoRepository;
import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;
import eu.campesinux.GestionePratiche.utenti.Utente;

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
		for (Avanzamento avanzamento : avanzamenti) {
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

	public Page<Pratica> listNuove() {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica nuova = statoService.findByStato(StatoPratica.STATO_NUOVA);
		return repo.findByStato(nuova, pageRequest);
	}

	public List<Pratica> listByCliente(Cliente cliente) {
		return repo.findByCliente(cliente);
	}

	public Page<Pratica> listDaMettereInScadenza() {
		Pageable pageRequest = PageRequest.of(0, 2);
		return repo.findDaMettereInScadenza(pageRequest);
	}

	public List<Pratica> listDaMettereInScadenzaPerJob() {
		return repo.findDaMettereInScadenza();
	}

	public Page<Pratica> listDaMettereScadute() {
		Pageable pageRequest = PageRequest.of(0, 2);
		return repo.findDaMettereScadute(pageRequest);
	}

	public List<Pratica> listDaMettereScadutePerJob() {
		return repo.findDaMettereScadute();
	}

	public List<Pratica> listByUtente(Utente utente) {
		return repo.findByUtenti(utente);
	}

}
