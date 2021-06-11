package it.epicode.be.persistence;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Cliente;

@Component
public interface ClienteRepo extends PagingAndSortingRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:nome%")
	public Page<Cliente> findByNomeContatto(String nome, Pageable page);
	
	@Query("Select c FROM Cliente c ORDER BY c.sedeLegale.comune.provincia.id ASC")
	public Page<Cliente> OrderByProvinciaLegale(Pageable page);
	
	@Query("Select c FROM Cliente c WHERE c.fatturatoAnnuale = :fatturato")
	public Page<Cliente> GetClientiPerFatturato(Pageable page, BigDecimal fatturato);
	
	@Query("Select c FROM Cliente c WHERE c.dataInserimento >= :dataInizio AND c.dataInserimento < :dataFine")
	public Page<Cliente> findClientiByInserimento(Pageable page, Date dataInizio, Date dataFine); 
	
	@Query("Select c FROM Cliente c WHERE c.dataUltimoContatto >= :dataInizio AND c.dataUltimoContatto < :dataFine")
	public Page<Cliente> findClientiByUltimoContatto(Pageable page, Date dataInizio, Date dataFine); 
	
	public Page<Cliente> findByRagioneSociale(Pageable page, String ragioneSociale);
	
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale >= :minimo AND c.fatturatoAnnuale <= :massimo")
	public Page<Cliente> findByRangeFatturato(Pageable page, BigDecimal minimo, BigDecimal massimo);
}
