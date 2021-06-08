package it.epicode.be.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.logic.RuoloService;
import it.epicode.be.model.Ruolo;

@RestController
@RequestMapping("/api/ruolo")
public class RuoloController {

	@Autowired
	private RuoloService rService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Ruolo> saveRuolo(@RequestBody Ruolo r) {
		Ruolo nuovoRuolo = rService.saveRuolo(r);
		return new ResponseEntity<Ruolo>(nuovoRuolo, HttpStatus.OK);
	}
	
}
