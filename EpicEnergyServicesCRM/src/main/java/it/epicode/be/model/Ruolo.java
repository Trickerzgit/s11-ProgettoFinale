package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ees_ruolo")
public class Ruolo {
	
	@Id
	@SequenceGenerator(name="ees_ruolo_seq", sequenceName="ees_ruolo_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_ruolo_seq")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoRuolo tipoRuolo;

}
