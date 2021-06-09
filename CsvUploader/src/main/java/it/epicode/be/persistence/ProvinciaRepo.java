package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Provincia;

@Component
public interface ProvinciaRepo extends JpaRepository<Provincia, Long>{

}
