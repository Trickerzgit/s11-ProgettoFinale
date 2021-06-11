package it.epicode.be.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




import lombok.Data;

@Data
@Entity
@Table(name="ees_fattura")
public class Fattura {

	private Integer anno;
	private Date data;
	private BigDecimal importo;
	@Id
	@SequenceGenerator(name="ees_fattura_seq", sequenceName="ees_fattura_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_fattura_seq")
	private Integer numero;
	@OneToOne(cascade = CascadeType.ALL)
	private StatoFattura stato;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
}
