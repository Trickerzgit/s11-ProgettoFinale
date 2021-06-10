package it.epicode.be.logic;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Cliente;
import it.epicode.be.persistence.ClienteRepo;

@Component
@Service
public class ClienteService {

	@Autowired
	private ClienteRepo repoC;
	
	public Cliente getCliente(Long id) {
		return repoC.findById(id).get();
	}
	
	public Cliente saveCliente(Cliente c) {
		c.setDataInserimento(new Date());
		return repoC.save(c);
	}
	
	public Page<Cliente> getAll(Pageable page) {
		return repoC.findAll(page);
	}
	
	public void elimina(Long id) {
		repoC.deleteById(id);
	}
	
	public Page<Cliente> getClientePerNome(String nome, Pageable page) {
		return repoC.findByNomeContatto(nome, page);
	}
	
	public Cliente modifica(Cliente c, Long id) {
		Cliente daModificare = repoC.findById(id).get();
		daModificare.setCognomeContatto(c.getCognomeContatto());
		daModificare.setDataInserimento(c.getDataInserimento());
		daModificare.setDataUltimoContatto(c.getDataUltimoContatto());
		daModificare.setEmail(c.getEmail());
		daModificare.setEmailContatto(c.getEmailContatto());
		daModificare.setFatturatoAnnuale(c.getFatturatoAnnuale());
		daModificare.setFatture(c.getFatture());
		daModificare.setNomeContatto(c.getNomeContatto());
		daModificare.setPartitaIva(c.getPartitaIva());
		daModificare.setPec(c.getPec());
		daModificare.setRagioneSociale(c.getRagioneSociale());
		daModificare.setSedeLegale(c.getSedeLegale());
		daModificare.setSedeOperativa(c.getSedeOperativa());
		daModificare.setTelefonoContatto(c.getTelefonoContatto());
		daModificare.setTelefono(c.getTelefono());
		daModificare.setTipo(c.getTipo());
		repoC.save(daModificare);
		return daModificare;
	}
	
	public Page<Cliente> getClientiByOrder(Pageable page) {
		return repoC.OrderByProvinciaLegale(page);
	}
	
	public Page<Cliente> getClientiPerFatturato(Pageable page, BigDecimal fatturato) {
		return repoC.GetClientiPerFatturato(page, fatturato);
	}
	
	public Page<Cliente> getClientiPerDataInserimento(Pageable page, Date dataInizio, Date dataFine) {
		return repoC.findClientiByInserimento(page, dataInizio, dataFine);
	}
	
	public Page<Cliente> getClientiPerUltimoContatto(Pageable page, Date dataInizio, Date dataFine) {
		return repoC.findClientiByUltimoContatto(page, dataInizio, dataFine);
	}
}
