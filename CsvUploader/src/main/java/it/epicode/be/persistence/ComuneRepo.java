package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Comune;

public interface ComuneRepo extends JpaRepository<Comune, Long>{

}
