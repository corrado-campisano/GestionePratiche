package eu.campesinux.GestionePratiche.avanzamenti;

import java.time.LocalDateTime;
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

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime scadenza;
	
	private String descrizione;

	@OneToOne
	@JoinColumn(name = "statoPrecedente_id", referencedColumnName = "id")
	private StatoPratica statoPrecedente;

	@OneToOne
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

	

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDateTime scadenza) {
		this.scadenza = scadenza;
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
