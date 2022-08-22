package eu.campesinux.GestionePratiche.utenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository repo;

	public List<Utente> listAll() {
		return repo.findAll();
	}
	
	public List<Utente> listBySpecializzazione(Specializzazione spec) {
		return repo.findBySpecializzazioni(spec);
	}
	
	public void save(Utente professionista) {
		repo.save(professionista);
	}

	public Utente get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
