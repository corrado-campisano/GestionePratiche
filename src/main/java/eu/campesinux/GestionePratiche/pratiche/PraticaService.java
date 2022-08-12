package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Pratica> listAll() {		
		return repo.findAll();
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

	public List<Pratica> listInScadenza(StatoPraticaService statoService) {
		StatoPratica inScadenza = statoService.findByStato(StatoPratica.STATO_IN_SCADENZA);
		return repo.findByStato(inScadenza);
	}

	public List<Pratica> listDaFatturare(StatoPraticaService statoService) {
		StatoPratica daFatturare = statoService.findByStato(StatoPratica.STATO_DA_FATTURARE);
		return repo.findByStato(daFatturare);
	}
}
