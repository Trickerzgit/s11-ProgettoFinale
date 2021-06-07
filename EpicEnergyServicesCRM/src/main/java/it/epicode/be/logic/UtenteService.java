package it.epicode.be.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.model.RegistrazioneUtente;
import it.epicode.be.model.Ruolo;
import it.epicode.be.model.TipoRuolo;
import it.epicode.be.model.Utente;
import it.epicode.be.persistence.RuoloRepo;
import it.epicode.be.persistence.UtenteRepo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Data
public class UtenteService {

	@Autowired
	private UtenteRepo repoU;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private RuoloRepo repoR;
	
	public RegistrazioneUtente registraUtente(RegistrazioneUtente registrazione) {
		Utente user = new Utente();
		
		user.setUsername(registrazione.getUsername());
		user.setNomeCompleto(registrazione.getNomeCompleto());
		user.setEmail(registrazione.getEmail());
		String hashedPassword = encoder.encode(registrazione.getPlainPassword());
		user.setPassword(hashedPassword);
		
		Set<Ruolo> ruoli = new HashSet<>();
		for (String s : registrazione.getNomiRuolo()) {
			TipoRuolo rt = TipoRuolo.valueOf(s);
			List<Ruolo> lista = repoR.findByTipoRuolo(rt);
			Ruolo r = lista.get(0);
			ruoli.add(r);
		}
		user.setRuoli(ruoli);
		repoU.save(user);
		
		user.getId();
		registrazione.setId(user.getId());
		log.info("Utente " + "'" + user.getUsername() + "'" + " salvato correttamente.");
		
		return registrazione;
	}
	
}
