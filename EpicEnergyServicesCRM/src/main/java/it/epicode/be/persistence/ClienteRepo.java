package it.epicode.be.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Cliente;

@Component
public interface ClienteRepo extends PagingAndSortingRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE %:nome%")
	public Page<Cliente> findByNomeContatto(String nome, Pageable page);

}
