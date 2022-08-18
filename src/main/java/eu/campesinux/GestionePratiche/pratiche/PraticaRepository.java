package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;

public interface PraticaRepository extends PagingAndSortingRepository<Pratica, Long> {

	Page<Pratica> findAll(Pageable pageable);

	Page<Pratica> findByStato(StatoPratica stato, Pageable pageable);

	List<Pratica> findByProfessionisti(Professionista prof);

}
