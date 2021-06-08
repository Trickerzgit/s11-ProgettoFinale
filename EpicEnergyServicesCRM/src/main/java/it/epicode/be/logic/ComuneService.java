package it.epicode.be.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Comune;
import it.epicode.be.persistence.ComuneRepo;

@Component
@Service
public class ComuneService {

	@Autowired
	private ComuneRepo repoC;
	
	public Comune getComune(Long id) {
		return repoC.findById(id).get();
	}
	
	public Comune saveComune(Comune c) {
		return repoC.save(c);
	}
	
	public Page<Comune> getAll(Pageable page) {
		return repoC.findAll(page);
	}
	
	public void elimina(Long id) {
		repoC.deleteById(id);
	}
	
	public Comune modifica(Comune c, Long id) {
		Comune daModificare = repoC.findById(id).get();
		daModificare.setNome(c.getNome());
		daModificare.setProvincia(c.getProvincia());
		repoC.save(daModificare);
		return daModificare;
	}
	
	
	
}
