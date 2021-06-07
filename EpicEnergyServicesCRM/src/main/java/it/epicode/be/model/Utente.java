package it.epicode.be.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity(name="ees_utente")
public class Utente implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ees_utente_seq")
	@SequenceGenerator(name="ees_utente_seq", sequenceName = "ees_utente_seq")
	private Long id;
	private String username;
	private String nomeCompleto;
	private LocalDate dataDiNascita;
	private boolean active = true;
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	private Date expirationTime;
	
	private String password;
	private String email;
	
	@ManyToMany
	@Cascade(CascadeType.DELETE)
	@JoinTable(name = "ees_utente_ruoli", joinColumns = @JoinColumn, inverseJoinColumns = @JoinColumn)
	private Set<Ruolo> ruoli = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRuoli().stream()
				.map(role -> new SimpleGrantedAuthority(role.getTipoRuolo().name()))
				.collect(Collectors.toList());
	}

}
