package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ees_comune")
public class Comune {

	@Id
	@SequenceGenerator(name="ees_comune_seq", sequenceName="ees_comune_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_comune_seq")
	private Long codice;
	private String nome;
	
	@ManyToOne
	private Provincia provincia;
	
	
}