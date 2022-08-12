package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;

public interface PraticaRepository extends JpaRepository<Pratica, Long> {

	List<Pratica> findByStato(StatoPratica stato);

}
