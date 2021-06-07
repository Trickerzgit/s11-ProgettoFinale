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
@Table(name="ees_statoFattura")
public class StatoFattura {

	@Id
	@SequenceGenerator(name="ees_statofattura_seq", sequenceName="ees_statofattura_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_statofattura_seq")
	private Long id;
	private String tipoStato;
	
}
