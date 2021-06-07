package it.epicode.be.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Utente;
import it.epicode.be.persistence.UtenteRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UtenteRepo repoU;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente u = repoU.findUtenteByUsername(username);
	
		if (u != null) {
			return u;
		}
		else {
			throw new UsernameNotFoundException("Utente non trovato con l'username: " + username);
		}
	}

}
