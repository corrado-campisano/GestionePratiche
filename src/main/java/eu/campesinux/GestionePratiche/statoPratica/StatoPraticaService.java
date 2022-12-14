package eu.campesinux.GestionePratiche.statoPratica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatoPraticaService {
	@Autowired
	private StatoPraticaRepository repo;
	
	public List<StatoPratica> listAll() {		
		return repo.findAll();
	}
	
	public void save(StatoPratica statoPratica) {
		repo.save(statoPratica);
	}
	
	public StatoPratica get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public StatoPratica findByStato(String stato) {
		return repo.findByStato(stato);
	}
}
