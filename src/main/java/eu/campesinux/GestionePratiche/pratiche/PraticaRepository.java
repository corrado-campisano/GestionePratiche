package eu.campesinux.GestionePratiche.pratiche;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.utenti.Utente;

public interface PraticaRepository extends PagingAndSortingRepository<Pratica, Long> {

	Page<Pratica> findAll(Pageable pageable);

	Page<Pratica> findByStato(StatoPratica stato, Pageable pageable);

	List<Pratica> findByStato(StatoPratica stato);
	
	List<Pratica> findByProfessionisti(Professionista prof);

	List<Pratica> findByUtenti(Utente utente);

	List<Pratica> findByCliente(Cliente cliente);
	
	@Query(value = "SELECT * FROM pratica p, stato_pratica sp WHERE "
					+ "p.stato_id = sp.id AND ("
					+ "sp.stato = '" + StatoPratica.STATO_IN_LAVORAZIONE + "' OR "
					+ "sp.stato = '" + StatoPratica.STATO_DA_NOTIFICARE + "' OR "
					+ "sp.stato = '" + StatoPratica.STATO_DA_DEPOSITARE + "' OR "
					+ "sp.stato = '" + StatoPratica.STATO_IN_DIBATTIMENTO + "' OR "
					+ "sp.stato = '" + StatoPratica.STATO_DA_FATTURARE + "')",
			  nativeQuery = true)
	Page<Pratica> findDaMettereInScadenza(Pageable pageable);
	
	@Query(value = "SELECT * FROM pratica p, stato_pratica sp WHERE "
			+ "p.stato_id = sp.id AND ("
			+ "sp.stato = '" + StatoPratica.STATO_IN_LAVORAZIONE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_NOTIFICARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_DEPOSITARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_IN_DIBATTIMENTO + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_IN_SCADENZA + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_FATTURARE + "')",
			nativeQuery = true)
	Page<Pratica> findDaMettereScadute(Pageable pageRequest);
			
	@Query(value = "SELECT * FROM pratica p, stato_pratica sp WHERE "
			+ "p.stato_id = sp.id AND ("
			+ "sp.stato = '" + StatoPratica.STATO_IN_LAVORAZIONE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_NOTIFICARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_DEPOSITARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_IN_DIBATTIMENTO + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_FATTURARE + "')",
			nativeQuery = true)
	List<Pratica> findDaMettereInScadenza();
	
	@Query(value = "SELECT * FROM pratica p, stato_pratica sp WHERE "
			+ "p.stato_id = sp.id AND ("
			+ "sp.stato = '" + StatoPratica.STATO_IN_LAVORAZIONE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_NOTIFICARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_DEPOSITARE + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_IN_DIBATTIMENTO + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_IN_SCADENZA + "' OR "
			+ "sp.stato = '" + StatoPratica.STATO_DA_FATTURARE + "')",
			nativeQuery = true)
	List<Pratica> findDaMettereScadute();
	
}
