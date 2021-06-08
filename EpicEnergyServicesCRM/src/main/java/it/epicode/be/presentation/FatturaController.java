package it.epicode.be.presentation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.logic.FatturaService;
import it.epicode.be.model.Fattura;

@RestController
@RequestMapping("/api/fattura")
public class FatturaController {
	
	@Autowired
	private FatturaService fService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Fattura> saveCliente(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Fattura f ) {
		Fattura nuovoFattura = fService.saveFattura(f);
		return new ResponseEntity<Fattura>(nuovoFattura, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("id") Integer id) {
		if (!fService.getFattura(id).equals(null)) {
			fService.elimina(id);
			return "Fattura elminata";
		}
		else {
			return "Fattura non esistente";
		}
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getAll(Pageable page) {
		Page<Fattura> result = fService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getby/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByCliente(@PathVariable("id")Long id, Pageable page) {
		Page<Fattura> result = fService.getByCliente(id, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/stato/{stato}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByStato(@PathVariable("stato")String stato, Pageable page) {
		Page<Fattura> result = fService.getByStato(stato, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/data/{data}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByData(@PathVariable("data") @DateTimeFormat(pattern = "yyyy-MM-dd")Date data, Pageable page) {
		Page<Fattura> result = fService.getByData(data, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/anno/{anno}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByAnno(@PathVariable("anno") int anno, Pageable page) {
		Page<Fattura> result = fService.getByAnno(anno, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/data/anno/{anno}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByDataAnno(@PathVariable("anno") int anno, Pageable page) {
		Page<Fattura> result = fService.getByDataAnno(anno, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/range/{minimo}/{massimo}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Fattura>> getByRange(@PathVariable("minimo") BigDecimal minimo, @PathVariable("massimo") BigDecimal massimo, Pageable page) {
		Page<Fattura> result = fService.getByRange(minimo, massimo, page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Fattura> getFatturaById(@PathVariable("id") Integer id) {
		Fattura f = fService.getFattura(id);
		if (!f.equals(null)) {
			return new ResponseEntity<>(f, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Fattura> updateFattura(@RequestBody Fattura f, @PathVariable("id") Integer id) {
		if (fService.getFattura(id).equals(null)) {
			return new ResponseEntity<Fattura>(HttpStatus.NO_CONTENT);
		}
		else {
			Fattura modificato = fService.modifica(f, id);
			return new ResponseEntity<Fattura>(modificato, HttpStatus.OK);
		}
	}

}
