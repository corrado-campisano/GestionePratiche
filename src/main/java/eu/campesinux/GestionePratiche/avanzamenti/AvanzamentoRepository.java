package eu.campesinux.GestionePratiche.avanzamenti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.campesinux.GestionePratiche.pratiche.Pratica;

public interface AvanzamentoRepository extends JpaRepository<Avanzamento, Long> {

	List<Avanzamento> findByPraticaId(Long pratica_id);

	List<Avanzamento> findByPratica(Pratica pratica);
}
