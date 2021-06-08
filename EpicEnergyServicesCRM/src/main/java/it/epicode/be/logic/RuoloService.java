package it.epicode.be.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Ruolo;
import it.epicode.be.persistence.RuoloRepo;

@Component
@Service
public class RuoloService {
	
	@Autowired
	private RuoloRepo repoR;
	
	public Ruolo saveRuolo(Ruolo r) {
		return repoR.save(r);
	}

}
