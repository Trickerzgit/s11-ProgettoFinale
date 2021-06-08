package it.epicode.be.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Indirizzo;
import it.epicode.be.persistence.IndirizzoRepo;

@Component
@Service
public class IndirizzoService {

	@Autowired
	private IndirizzoRepo repoI;
	
	public Indirizzo getIndirizzo(Long id) {
		return repoI.findById(id).get();
	}
	
	public Indirizzo saveIndirizzo(Indirizzo i) {
		return repoI.save(i);
	}
	
	public Page<Indirizzo> getAll(Pageable page) {
		return repoI.findAll(page);
	}
	
	public void elimina(Long id) {
		repoI.deleteById(id);
	}
	
	public Indirizzo modifica(Indirizzo i, Long id) {
		Indirizzo daModificare = repoI.findById(id).get();
		daModificare.setCap(i.getCap());
		daModificare.setCivico(i.getCivico());
		daModificare.setComune(i.getComune());
		daModificare.setLocalita(i.getLocalita());
		daModificare.setVia(i.getVia());
		repoI.save(daModificare);
		return daModificare;
	}
}
