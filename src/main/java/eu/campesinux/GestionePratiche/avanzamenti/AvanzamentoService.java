package eu.campesinux.GestionePratiche.avanzamenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvanzamentoService {

	@Autowired
	private AvanzamentoRepository repo;

	public List<Avanzamento> listAll() {
		return repo.findAll();
	}

	public void save(Avanzamento avanzamento) {
		repo.save(avanzamento);
	}

	public Avanzamento get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
