package it.epicode.be.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.StatoFattura;
import it.epicode.be.persistence.StatoFatturaRepo;

@Component
@Service
public class StatoFatturaService {
	
	@Autowired
	private StatoFatturaRepo repoSF;
	
	public StatoFattura saveStato(StatoFattura sf) {
		return repoSF.save(sf);
		
	}

}
