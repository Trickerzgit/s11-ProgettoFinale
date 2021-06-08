package it.epicode.be.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import it.epicode.be.logic.IndirizzoService;
import it.epicode.be.model.Indirizzo;

@RequestMapping("/api/indirizzo")
@RestController
public class IndirizzoController {
	
	@Autowired
	private IndirizzoService iService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Indirizzo> saveCliente(@RequestBody Indirizzo i) {
		Indirizzo nuovoCliente = iService.saveIndirizzo(i);
		return new ResponseEntity<Indirizzo>(nuovoCliente, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("id") Long id) {
		if (!iService.getIndirizzo(id).equals(null)) {
			iService.elimina(id);
			return "Indirizzo eliminato";
		}
		else {
			return "Indirizzo non esistente";
		}
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Indirizzo>> getAll(Pageable page) {
		Page<Indirizzo> result = iService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Indirizzo> getIndirizzoById(@PathVariable("id") Long id) {
		Indirizzo c = iService.getIndirizzo(id);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Indirizzo> updateIndirizzo(@RequestBody Indirizzo c, @PathVariable("id") Long id) {
		if (iService.getIndirizzo(id).equals(null)) {
			return new ResponseEntity<Indirizzo>(HttpStatus.NO_CONTENT);
		}
		else {
			Indirizzo modificato = iService.modifica(c, id);
			return new ResponseEntity<Indirizzo>(modificato, HttpStatus.OK);
		}
	}

}
