package it.epicode.be.utility;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import it.epicode.be.persistence.ComuneRepo;
import it.epicode.be.persistence.ProvinciaRepo;

@Service
public class CSVService {

	@Autowired
	ProvinciaRepo repoP;
	
	@Autowired 
	ComuneRepo repoC;
	
	public void save(MultipartFile file) {
		try {
			List<Provincia> province = CSVHelper.csvToProvincia(file.getInputStream());
			repoP.saveAll(province);
		}
		catch (IOException e) {
			throw new RuntimeException("Salvataggio dati CSV fallito: " + e.getMessage());
		}
	}
	
	public void saveComuni(MultipartFile file) {
		List<Provincia> province = repoP.findAll();
		try {
			List<Comune> comuni = CSVHelper.csvToComune(file.getInputStream(), province);
			
			repoC.saveAll(comuni);
		}
		catch (IOException e) {
			throw new RuntimeException("Salvataggio dati CSV fallito: " + e.getMessage());
		}
	}
	
	public List<Provincia> getAll() {
		return repoP.findAll();
	}
	
}
