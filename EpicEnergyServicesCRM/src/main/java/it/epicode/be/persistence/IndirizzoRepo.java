package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Indirizzo;

public interface IndirizzoRepo extends JpaRepository<Indirizzo, Long>{

}
