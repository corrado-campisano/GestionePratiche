package eu.campesinux.GestionePratiche.professionisti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionistaService {

	@Autowired
	private ProfessionistaRepository repo;

	public List<Professionista> listAll() {
		return repo.findAll();
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
