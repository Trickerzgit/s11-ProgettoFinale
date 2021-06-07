package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Cliente;

@Component
public interface ClienteRepo extends JpaRepository<Cliente, Long>{

}
