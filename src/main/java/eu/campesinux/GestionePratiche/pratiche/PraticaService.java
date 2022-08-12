package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoRepository;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPraticaService;

@Service
public class PraticaService {
	@Autowired
	private PraticaRepository repo;
	
	@Autowired
	private AvanzamentoRepository avaRepo;
	
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

	public Page<Pratica> listInScadenza(StatoPraticaService statoService) {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica inScadenza = statoService.findByStato(StatoPratica.STATO_IN_SCADENZA);
		return repo.findByStato(inScadenza, pageRequest);
	}

	public Page<Pratica> listDaFatturare(StatoPraticaService statoService) {
		Pageable pageRequest = PageRequest.of(0, 2);
		StatoPratica daFatturare = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		return repo.findByStato(daFatturare, pageRequest);
	}
}
