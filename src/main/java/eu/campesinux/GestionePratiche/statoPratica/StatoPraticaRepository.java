package eu.campesinux.GestionePratiche.statoPratica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoPraticaRepository extends JpaRepository<StatoPratica, Long> {

	StatoPratica findByStato(String stato);
}
