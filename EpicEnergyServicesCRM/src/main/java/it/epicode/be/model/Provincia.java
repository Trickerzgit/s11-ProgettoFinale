package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ees_provincia")
public class Provincia {
	
	@Id
	@SequenceGenerator(name="ees_provincia_seq", sequenceName="ees_provincia_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_provincia_seq")
	private Long id;
	private String sigla;
	private String nome;
	private String regione;

}
