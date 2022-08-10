package eu.campesinux.GestionePratiche.tipoPratica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPraticaService {
	@Autowired
	private TipoPraticaRepository repo;
	
	public List<TipoPratica> listAll() {		
		return repo.findAll();
	}
	
	public void save(TipoPratica pratica) {
		repo.save(pratica);
	}
	
	public TipoPratica get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
