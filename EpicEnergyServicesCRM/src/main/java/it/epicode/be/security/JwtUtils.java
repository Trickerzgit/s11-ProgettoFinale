package it.epicode.be.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.epicode.be.model.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expirationms}")
	private Long jwtExpirationMs;
	
	public String generaJwtToken(Authentication a) {
		Utente utente = (Utente) a.getPrincipal();
		Date adesso = new Date();
		Date scadenza = new Date((adesso).getTime() + jwtExpirationMs);
		utente.setExpirationTime(scadenza);
		return Jwts.builder()
				.setSubject((utente.getUsername()))
				.setIssuedAt(adesso)
				.setExpiration(scadenza)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUtenteFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validaToken(String authToken) {
		try {
			Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(authToken);
			return true;
		}
		catch (Exception e) {
			log.error("Firma jwt non valida: {}", e.getMessage());
		}
		return false;
	}
	
}

