package eu.campesinux.GestionePratiche.specializzazioni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.campesinux.GestionePratiche.utenti.Utente;

@Service
public class SpecializzazioneService {

	@Autowired
	private SpecializzazioneRepository repo;

	public List<Specializzazione> listAll() {
		return repo.findAll();
	}

	public void save(Specializzazione specializzazione) {
		repo.save(specializzazione);
	}

	public Specializzazione get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public List<Specializzazione> listByUtente(Utente utente) {
		return repo.findByUtenti(utente);
	}
}
