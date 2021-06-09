package it.epicode.be.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Provincia;
import it.epicode.be.persistence.ProvinciaRepo;

@Component
@Service
public class ProvinciaService {

	@Autowired
	private ProvinciaRepo repoP;
	
	public Provincia getProvincia(Long id) {
		return repoP.findById(id).get();
	}
	
	public Provincia saveProvincia(Provincia p) {
		return repoP.save(p);
	}
	
	public Page<Provincia> getAll(Pageable page) {
		return repoP.findAll(page);
	}
	
	public void elimina(Long id) {
		repoP.deleteById(id);
	}
	
	public Provincia modifica(Provincia p, Long id) {
		Provincia daModificare = repoP.findById(id).get();
		daModificare.setNome(p.getNome());
		daModificare.setRegione(p.getRegione());
		daModificare.setSigla(p.getSigla());
		repoP.save(daModificare);
		return daModificare;
	}
}
