package eu.campesinux.GestionePratiche.ruoli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloService {
	@Autowired
	private RuoloRepository repo;
	
	public List<Ruolo> listAll() {		
		return repo.findAll();
	}
	
	public void save(Ruolo ruolo) {
		repo.save(ruolo);
	}
	
	public Ruolo get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Ruolo findByRuolo(String ruolo) {
		return repo.findByRuolo(ruolo);
	}
}
