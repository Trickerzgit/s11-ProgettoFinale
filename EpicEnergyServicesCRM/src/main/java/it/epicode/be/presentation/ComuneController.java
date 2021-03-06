package it.epicode.be.presentation;


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

import it.epicode.be.logic.ComuneService;
import it.epicode.be.model.Comune;

@RestController
@RequestMapping("/api/comune")
public class ComuneController {

	@Autowired
	private ComuneService cService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Comune> saveComune(@RequestBody Comune c) {
		Comune nuovoComune = cService.saveComune(c);
		return new ResponseEntity<Comune>(nuovoComune, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{codice}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("codice") Long id) {
		if (!cService.getComune(id).equals(null)) {
			cService.elimina(id);
			return "Comune eliminato";
		}
		else {
			return "Comune non esistente";
		}
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Comune>> getAll(Pageable page) {
		Page<Comune> result = cService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{codice}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Comune> getComuneById(@PathVariable("codice") Long id) {
		Comune c = cService.getComune(id);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{codice}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Comune> updateComune(@RequestBody Comune c, @PathVariable("codice") Long id) {
		if (cService.getComune(id).equals(null)) {
			return new ResponseEntity<Comune>(HttpStatus.NO_CONTENT);
		}
		else {
			Comune modificato = cService.modifica(c, id);
			return new ResponseEntity<Comune>(modificato, HttpStatus.OK);
		}
	}
	
}
