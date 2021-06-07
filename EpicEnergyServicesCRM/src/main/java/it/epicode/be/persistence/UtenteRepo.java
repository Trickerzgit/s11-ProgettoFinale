package it.epicode.be.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.model.Utente;

public interface UtenteRepo extends JpaRepository<Utente, Long>{
	
	@Query("SELECT u FROM ees_utente u WHERE u.username = :username")
	public Utente findUtenteByUsername(String username);

}
