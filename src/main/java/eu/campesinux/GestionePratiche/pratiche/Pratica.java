package eu.campesinux.GestionePratiche.pratiche;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pratica {

	private Long id;
	private String identificativo;
	private String descrizione;

	protected Pratica() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
