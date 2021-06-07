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

import it.epicode.be.logic.ClienteService;
import it.epicode.be.model.Cliente;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente c) {
		Cliente nuovoCliente = clService.saveCliente(c);
		return new ResponseEntity<Cliente>(nuovoCliente, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		if (!clService.getCliente(id).equals(null)) {
			clService.elimina(id);
			return "Cliente eliminato";
		}
		else {
			return "Cliente non esistente";
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> result = clService.getAll();
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
		Cliente c = clService.getCliente(id);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente c, @PathVariable("id") Long id) {
		if (clService.getCliente(id).equals(null)) {
			return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		}
		else {
			Cliente modificato = clService.modifica(c, id);
			return new ResponseEntity<Cliente>(modificato, HttpStatus.OK);
		}
	}
}
