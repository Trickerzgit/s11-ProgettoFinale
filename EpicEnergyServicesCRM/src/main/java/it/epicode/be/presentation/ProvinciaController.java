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

import it.epicode.be.logic.ProvinciaService;
import it.epicode.be.model.Provincia;

@RestController
@RequestMapping("/api/provincia")
public class ProvinciaController {

	@Autowired
	private ProvinciaService prService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Provincia> saveProvincia(@RequestBody Provincia c) {
		Provincia nuovaProvincia = prService.saveProvincia(c);
		return new ResponseEntity<Provincia>(nuovaProvincia, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("id") Long id) {
		if (!prService.getProvincia(id).equals(null)) {
			prService.elimina(id);
			return "Provincia eliminata";
		}
		else {
			return "Provincia non esistente";
		}
	}
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Provincia>> getAll(Pageable page) {
		Page<Provincia> result = prService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Provincia> getProvinciaById(@PathVariable("id") Long id) {
		Provincia c = prService.getProvincia(id);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Provincia> updateProvincia(@RequestBody Provincia c, @PathVariable("id") Long id) {
		if (prService.getProvincia(id).equals(null)) {
			return new ResponseEntity<Provincia>(HttpStatus.NO_CONTENT);
		}
		else {
			Provincia modificato = prService.modifica(c, id);
			return new ResponseEntity<Provincia>(modificato, HttpStatus.OK);
		}
	}
	
	
	
}
