package it.epicode.be.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Fattura> saveCliente(@RequestBody Fattura f) {
		Fattura nuovoFattura = fService.saveFattura(f);
		return new ResponseEntity<Fattura>(nuovoFattura, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
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
	public ResponseEntity<List<Fattura>> getAll() {
		List<Fattura> result = fService.getAll();
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
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
