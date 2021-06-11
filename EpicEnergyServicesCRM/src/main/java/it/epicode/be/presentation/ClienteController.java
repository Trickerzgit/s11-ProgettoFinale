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
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.logic.ClienteService;
import it.epicode.be.model.Cliente;

@Component
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clService;
	
	@RequestMapping(value="/inserisci", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Cliente> saveCliente(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")Cliente c) {
		Cliente nuovoCliente = clService.saveCliente(c);
		return new ResponseEntity<Cliente>(nuovoCliente, HttpStatus.OK);
	}
	
	@RequestMapping(value="/elimina/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getAll(Pageable page) {
		Page<Cliente> result = clService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/order/province")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getAllByProvince(Pageable page) {
		Page<Cliente> result = clService.getAll(page);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
		Cliente c = clService.getCliente(id);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/get/ragione/{ragione}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getClienteByRagione(@PathVariable("ragione") String ragione, Pageable page) {
		Page<Cliente> c = clService.getClienteByRagioneSociale(page, ragione);
		if (!c.equals(null)) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/get/fatturato/{importo}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getClientiByFatturato(@PathVariable("importo") BigDecimal importo, Pageable page) {
		Page<Cliente> result = clService.getClientiPerFatturato(page, importo);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/inserimento/{datainizio}/{datafine}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getClientiByInserimento(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("datainizio") Date dataInizio, @PathVariable("datafine") Date dataFine, Pageable page) {
		Page<Cliente> result = clService.getClientiPerDataInserimento(page, dataInizio, dataFine);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/ultimo/{datainizio}/{datafine}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getClientiByUltimoContatto(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("datainizio") Date dataInizio, @PathVariable("datafine") Date dataFine, Pageable page) {
		Page<Cliente> result = clService.getClientiPerUltimoContatto(page, dataInizio, dataFine);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("get/range/{minimo}/{massimo}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getByRangeFatturato(@PathVariable("minimo") BigDecimal minimo, @PathVariable("massimo") BigDecimal massimo, Pageable page) {
		Page<Cliente> result = clService.getClienteByRangeFatturato(page, minimo, massimo);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getby/{nome}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Cliente>> getClientePerNome(@PathVariable("nome") String nome, Pageable page) {
		Page<Cliente> result = clService.getClientePerNome(nome, page);
		if (!result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
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
