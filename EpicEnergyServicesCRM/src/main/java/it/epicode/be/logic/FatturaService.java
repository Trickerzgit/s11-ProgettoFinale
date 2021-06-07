package it.epicode.be.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Fattura;
import it.epicode.be.persistence.FatturaRepo;

@Component
@Service
public class FatturaService {

	@Autowired
	private FatturaRepo repoF;
	
	public Fattura getFattura(Integer id) {
		return repoF.findById(id).get();
	}
	
	public Fattura saveFattura(Fattura f) {
		return repoF.save(f);
	}
	
	public List<Fattura> getAll() {
		return repoF.findAll();
	}
	
	public void elimina(Integer id) {
		repoF.deleteById(id);
	}
	
	public Fattura modifica(Fattura f, Integer id) {
		Fattura daModificare = repoF.findById(id).get();
		daModificare.setAnno(f.getAnno());
		daModificare.setData(f.getData());
		daModificare.setImporto(f.getImporto());
		repoF.save(daModificare);
		return daModificare;
	}
}
