package it.epicode.be.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Ruolo;
import it.epicode.be.model.TipoRuolo;

public interface RuoloRepo extends JpaRepository<Ruolo, Long>{

	public List<Ruolo> findByTipoRuolo(TipoRuolo ruolo);
	 
}
