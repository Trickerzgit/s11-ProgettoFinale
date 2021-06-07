package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Fattura;

public interface FatturaRepo extends JpaRepository<Fattura, Integer>{

}
