package eu.campesinux.GestionePratiche.avanzamenti;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import eu.campesinux.GestionePratiche.pratiche.Pratica;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;

@Entity
public class Avanzamento {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne
	@JoinColumn (name="pratica_id")
	private Pratica pratica;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	private String descrizione;

	@OneToOne// TODO : restore? (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "statoPrecedente_id", referencedColumnName = "id")
	private StatoPratica statoPrecedente;

	@OneToOne// TODO : restore? (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "statoAttuale_id", referencedColumnName = "id")
	private StatoPratica statoAttuale;

	public Avanzamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public StatoPratica getStatoPrecedente() {
		return statoPrecedente;
	}

	public void setStatoPrecedente(StatoPratica statoPrecedente) {
		this.statoPrecedente = statoPrecedente;
	}

	public StatoPratica getStatoAttuale() {
		return statoAttuale;
	}

	public void setStatoAttuale(StatoPratica statoAttuale) {
		this.statoAttuale = statoAttuale;
	}
	
	public Pratica getPratica() {
		return pratica;
	}

	public void setPratica(Pratica pratica) {
		this.pratica = pratica;
	}
}
