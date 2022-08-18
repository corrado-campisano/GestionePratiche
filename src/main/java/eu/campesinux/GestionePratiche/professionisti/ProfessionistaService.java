package eu.campesinux.GestionePratiche.professionisti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

@Service
public class ProfessionistaService {

	@Autowired
	private ProfessionistaRepository repo;

	public List<Professionista> listAll() {
		return repo.findAll();
	}
	
	public List<Professionista> listBySpecializzazione(Specializzazione spec) {
		return repo.findBySpecializzazioni(spec);
	}
	
	public void save(Professionista professionista) {
		repo.save(professionista);
	}

	public Professionista get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
