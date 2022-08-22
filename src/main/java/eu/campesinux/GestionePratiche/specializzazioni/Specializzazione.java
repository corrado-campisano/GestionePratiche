package eu.campesinux.GestionePratiche.specializzazioni;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.utenti.Utente;

@Entity
public class Specializzazione {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	
	@ManyToMany(mappedBy = "specializzazioni")
	List<Professionista> professionisti;

	@ManyToMany(mappedBy = "specializzazioni")
	List<Utente> utenti;
	
	public Specializzazione() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


}
