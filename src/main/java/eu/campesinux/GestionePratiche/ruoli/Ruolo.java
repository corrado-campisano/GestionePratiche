package eu.campesinux.GestionePratiche.ruoli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ruoli")
public class Ruolo {

	public static final String RUOLO_ADMIN = "ADMIN";           
	public static final String RUOLO_USER = "USER";       
	public static final String RUOLO_MANAGER = "MANAGER";  
	public static final String RUOLO_PROFESSIONISTA = "PROFESSIONISTA";    
	
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ruolo;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return this.ruolo;
	}
}
