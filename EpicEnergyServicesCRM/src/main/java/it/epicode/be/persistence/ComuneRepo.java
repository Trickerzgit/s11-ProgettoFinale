package it.epicode.be.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Comune;

@Component
public interface ComuneRepo extends PagingAndSortingRepository<Comune, Long>{

}
