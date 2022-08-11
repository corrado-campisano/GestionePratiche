package eu.campesinux.GestionePratiche.professionisti;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import eu.campesinux.GestionePratiche.specializzazioni.Specializzazione;

@Entity
@Table(name="professionista")
@Access(AccessType.FIELD)
public class Professionista {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cognome;
	
	@ManyToMany// TODO : restore? (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Specializzazione.class)
	@JoinTable(name = "professionista_specializzazione", joinColumns = @JoinColumn(name = "professionista_id"),	inverseJoinColumns = @JoinColumn(name = "specializzazione_id"))
	private Set<Specializzazione> specializzazioni= new HashSet<>();
	
	protected Professionista() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	
	public void addSpecializzazione(Specializzazione specializzazione) {
		this.specializzazioni.add(specializzazione);
	}
	
	public Set<Specializzazione> getSpecializzazioni(){
		return this.specializzazioni;
	}
	
	@Override
	public String toString() {
		String ret = this.nome + " " + this.cognome;
		
		if (this.specializzazioni.size()>0) {
			ret += " (";
			for(Specializzazione spec : this.specializzazioni) {
				ret += spec.getDescrizione();
			}
			ret += " )";
		}
		
		return ret;
	}
}
