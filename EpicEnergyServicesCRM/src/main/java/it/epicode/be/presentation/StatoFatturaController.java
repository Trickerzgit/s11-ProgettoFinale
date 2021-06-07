package it.epicode.be.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.logic.StatoFatturaService;
import it.epicode.be.model.StatoFattura;

@RestController
@RequestMapping("/api/stato_fattura")
public class StatoFatturaController {
	
	@Autowired
	private StatoFatturaService sfService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<StatoFattura> saveComune(@RequestBody StatoFattura c) {
		StatoFattura nuovoComune = sfService.saveStato(c);
		return new ResponseEntity<StatoFattura>(nuovoComune, HttpStatus.OK);
	}

}
