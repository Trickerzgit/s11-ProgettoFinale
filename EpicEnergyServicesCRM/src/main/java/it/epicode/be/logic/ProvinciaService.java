package it.epicode.be.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Provincia> getAll() {
		return repoP.findAll();
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
