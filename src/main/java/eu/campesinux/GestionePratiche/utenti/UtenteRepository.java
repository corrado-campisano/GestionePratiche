package eu.campesinux.GestionePratiche.utenti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

	List<Utente> findBySpecializzazioni(Specializzazione spec);

}
