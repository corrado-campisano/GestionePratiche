package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraticaService {
	@Autowired
	private PraticaRepository repo;
	
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
		repo.deleteById(id);
	}
}
