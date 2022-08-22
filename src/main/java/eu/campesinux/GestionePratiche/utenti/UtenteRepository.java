package eu.campesinux.GestionePratiche.utenti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.campesinux.GestionePratiche.ruoli.Ruolo;
import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

	List<Utente> findBySpecializzazioni(Specializzazione spec);

	@Query(value = "SELECT * FROM utenti u WHERE "
			+ "u.role = '" + Ruolo.RUOLO_PROFESSIONISTA + "'",
	  nativeQuery = true)
	List<Utente> findAllProfessionals();

	Utente findByUsername(String currentUserName);

}
