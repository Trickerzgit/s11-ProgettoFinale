package it.epicode.be.persistence;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.be.model.Fattura;

public interface FatturaRepo extends PagingAndSortingRepository<Fattura, Integer>{
	
	@Query("SELECT f FROM Fattura f WHERE f.cliente.id = :id")
	public Page<Fattura> findByCliente(Long id, Pageable page);
	
	@Query("SELECT f FROM Fattura f WHERE f.stato.tipoStato = :tipo")
	public Page<Fattura> findByStatus(String tipo, Pageable page);
	
	@Query("SELECT f FROM Fattura f WHERE f.data >= :dataInizio AND f.data < :dataFine")
	public Page<Fattura> findByData (Date dataInizio, Date dataFine, Pageable page);
	
	@Query("SELECT f FROM Fattura f WHERE year(f.data) = :anno")
	public Page<Fattura> findByYearDate (int anno, Pageable page);
	
	@Query("SELECT f FROM Fattura f WHERE f.anno = :anno")
	public Page<Fattura> findByYear (int anno, Pageable page);
	
	@Query("SELECT f FROM Fattura f WHERE f.importo > :minimo AND f.importo < :massimo")
	public Page<Fattura> findByRange (BigDecimal minimo, BigDecimal massimo, Pageable page);

}
