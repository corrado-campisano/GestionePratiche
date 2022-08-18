package eu.campesinux.GestionePratiche.specializzazioni;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.campesinux.GestionePratiche.professionisti.Professionista;

public interface SpecializzazioneRepository extends JpaRepository<Specializzazione, Long> {

	List<Specializzazione> findByProfessionisti(Professionista prof);

}
