package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Comune;

@Component
public interface ComuneRepo extends JpaRepository<Comune, Long>{

}
