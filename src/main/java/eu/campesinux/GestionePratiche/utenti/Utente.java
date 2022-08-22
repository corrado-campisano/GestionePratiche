package eu.campesinux.GestionePratiche.utenti;

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
@Table(name="utenti")
@Access(AccessType.FIELD)
public class Utente {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String role;
	private boolean enabled;
	
	
	@ManyToMany
	@JoinTable(name = "utente_specializzazione", joinColumns = @JoinColumn(name = "utente_id"),	inverseJoinColumns = @JoinColumn(name = "specializzazione_id"))
	private Set<Specializzazione> specializzazioni= new HashSet<>();
	
	protected Utente() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
