package it.epicode.be.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.be.model.Indirizzo;

public interface IndirizzoRepo extends PagingAndSortingRepository<Indirizzo, Long>{

}
