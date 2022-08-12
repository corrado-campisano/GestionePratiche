package eu.campesinux.GestionePratiche.statoPratica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatoPratica {

	public static final String STATO_NUOVA = "nuova";           
	public static final String STATO_RIGETTATA = "rigettata";       
	public static final String STATO_IN_LAVORAZIONE = "in lavorazione";  
	public static final String STATO_DA_NOTIFICARE = "da notificare";   
	public static final String STATO_DA_DEPOSITARE = "da depositare";   
	public static final String STATO_IN_SCADENZA = "in scadenza";     
	public static final String STATO_SCADUTA = "scaduta";         
	public static final String STATO_IN_DIBATTIMENTO = "in dibattimento"; 
	public static final String STATO_DA_FATTURARE = "da fatturare";    
	public static final String STATO_EVASA = "evasa";     
	
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stato;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	@Override
	public String toString() {
		return this.stato;
	}
}
