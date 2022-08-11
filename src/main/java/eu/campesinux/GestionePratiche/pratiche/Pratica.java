package eu.campesinux.GestionePratiche.pratiche;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import eu.campesinux.GestionePratiche.clienti.Cliente;
import eu.campesinux.GestionePratiche.professionisti.Professionista;
import eu.campesinux.GestionePratiche.statoPratica.StatoPratica;
import eu.campesinux.GestionePratiche.tipoPratica.TipoPratica;

@Entity
public class Pratica {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String identificativo;

	private String descrizione;

	// TODO: @ManyToMany should be OK here
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Professionista.class)
	@JoinTable(name = "pratica_professionista", joinColumns = @JoinColumn(name = "pratica_id"), inverseJoinColumns = @JoinColumn(name = "professionista_id"))
	private Set<Professionista> professionisti = new HashSet<>();

	// TODO: @OneToOne??
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;

	// TODO: @OneToOne??
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "stato_id", referencedColumnName = "id")
	private StatoPratica stato;

	// TODO: @OneToOne??
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipo_id", referencedColumnName = "id")
	private TipoPratica tipo;

	protected Pratica() {
	}

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

	public void addProfessionista(Professionista professionista) {
		this.professionisti.add(professionista);
	}

	public Set<Professionista> getProfessionisti() {
		return this.professionisti;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatoPratica getStato() {
		return stato;
	}

	public void setStato(StatoPratica stato) {
		this.stato = stato;
	}

	public TipoPratica getTipo() {
		return tipo;
	}

	public void setTipo(TipoPratica tipo) {
		this.tipo = tipo;
	}
}
