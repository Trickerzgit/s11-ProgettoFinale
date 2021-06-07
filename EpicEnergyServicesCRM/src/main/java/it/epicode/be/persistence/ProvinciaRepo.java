package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Provincia;

public interface ProvinciaRepo extends JpaRepository<Provincia, Long>{

}
