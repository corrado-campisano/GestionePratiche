package eu.campesinux.GestionePratiche.ruoli;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {

	Ruolo findByRuolo(String stato);
}
