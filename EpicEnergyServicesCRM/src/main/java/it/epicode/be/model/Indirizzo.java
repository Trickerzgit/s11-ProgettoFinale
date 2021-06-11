package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ees_indirizzo")
public class Indirizzo {
	
	@Id
	@SequenceGenerator(name="ees_indirizzo_seq", sequenceName="ees_indirizzo_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_indirizzo_seq")
	private Long id;
	private String via;
	private int civico;
	private String localita;
	private Long cap;
	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Comune comune;

}
