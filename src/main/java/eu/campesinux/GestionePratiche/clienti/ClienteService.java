package eu.campesinux.GestionePratiche.clienti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public List<Cliente> listAll() {
		return repo.findAll();
	}

	public void save(Cliente cliente) {
		repo.save(cliente);
	}

	public Cliente get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
