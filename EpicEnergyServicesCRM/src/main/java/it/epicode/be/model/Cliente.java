package it.epicode.be.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ees_cliente")
public class Cliente {
	
	@Id
	@SequenceGenerator(name="ees_cliente_seq", sequenceName="ees_cliente_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_cliente_seq")
	private Long id;
	private String ragioneSociale;
	private String partitaIva;
	private String email;
	private Date dataInserimento;
	private Date dataUltimoContatto;
	private BigDecimal fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeLegale;
	@OneToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeOperativa;
	
	@OneToMany
	private Set<Fattura> fatture;
	
	@Enumerated(EnumType.STRING)
	private TipoCliente tipo;
	
	
}
