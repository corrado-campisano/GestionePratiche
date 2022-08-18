package eu.campesinux.GestionePratiche.professionisti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

public interface ProfessionistaRepository extends JpaRepository<Professionista, Long> {

	List<Professionista> findBySpecializzazioni(Specializzazione spec);

}
