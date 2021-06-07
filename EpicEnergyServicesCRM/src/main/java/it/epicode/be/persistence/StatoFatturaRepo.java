package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.StatoFattura;

public interface StatoFatturaRepo extends JpaRepository<StatoFattura, Long>{

}
