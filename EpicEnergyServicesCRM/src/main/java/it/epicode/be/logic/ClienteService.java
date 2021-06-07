package it.epicode.be.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Cliente> getAll() {
		return repoC.findAll();
	}
	
	public void elimina(Long id) {
		repoC.deleteById(id);
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
	
}