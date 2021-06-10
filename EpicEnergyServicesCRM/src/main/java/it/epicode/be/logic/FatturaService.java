package it.epicode.be.logic;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Fattura> getAll(Pageable page) {
		return repoF.findAll(page);
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
	
	public Page<Fattura> getByCliente(Long id, Pageable page) {
		return repoF.findByCliente(id, page);
	}
	
	public Page<Fattura> getByStato(String tipo, Pageable page) {
		return repoF.findByStatus(tipo, page);
	}
	
	public Page<Fattura> getByData(Date dataInizio, Date dataFine, Pageable page) {
		return repoF.findByData(dataInizio, dataFine, page);
	}
	
	public Page<Fattura> getByAnno(int anno, Pageable page) {
		return repoF.findByYear(anno, page);
	}
	
	public Page<Fattura> getByDataAnno(int anno, Pageable page) {
		return repoF.findByYearDate(anno, page);
	}
	
	public Page<Fattura> getByRange(BigDecimal minimo, BigDecimal massimo, Pageable page) {
		return repoF.findByRange(minimo, massimo, page);
	}
	
}
