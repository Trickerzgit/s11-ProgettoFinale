package it.epicode.be.logic;

import java.util.List;

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
		List<StatoFattura> stati = repoSF.findAll();
		for (StatoFattura s : stati) {
			if (s.getTipoStato().equals(sf.getTipoStato())) {
				return null;
			}
				
		}
		return repoSF.save(sf);
		
		
	}
	
	public List<StatoFattura> getAll() {
		
		return repoSF.findAll();
	}

}
