package eu.campesinux.GestionePratiche.avanzamenti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvanzamentoRepository extends JpaRepository<Avanzamento, Long> {

	List<Avanzamento> findByPraticaId(Long pratica_id);
}
