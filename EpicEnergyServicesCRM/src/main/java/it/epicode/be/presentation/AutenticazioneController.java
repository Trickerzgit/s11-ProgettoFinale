package it.epicode.be.presentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.logic.UtenteService;
import it.epicode.be.login.LoginRequest;
import it.epicode.be.login.LoginResponse;
import it.epicode.be.model.RegistrazioneUtente;
import it.epicode.be.model.Utente;
import it.epicode.be.security.JwtUtils;

@RestController
@RequestMapping("/api/autenticazione")
public class AutenticazioneController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UtenteService uService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/registrazione")
	public RegistrazioneUtente registrazione(@RequestBody RegistrazioneUtente registrazione) {
		uService.registraUtente(registrazione);
		return registrazione;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> autenticazione(@RequestBody LoginRequest loginRequest) {
		Authentication autenticazione = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		autenticazione.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(autenticazione);
		String jwt = jwtUtils.generaJwtToken(autenticazione);
		
		Utente utente = (Utente) autenticazione.getPrincipal();
		List<String> ruoli = utente.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt, utente.getId(), utente.getUsername(), utente.getEmail(), ruoli, utente.getExpirationTime()));
	}


}
