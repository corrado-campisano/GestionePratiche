package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.avanzamenti.Avanzamento;
import eu.campesinux.GestionePratiche.avanzamenti.AvanzamentoRepository;

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
}
