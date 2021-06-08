package it.epicode.be.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.be.model.Provincia;

public interface ProvinciaRepo extends PagingAndSortingRepository<Provincia, Long>{

}
