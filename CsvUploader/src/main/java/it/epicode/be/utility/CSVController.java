package it.epicode.be.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.epicode.be.model.Provincia;

@Controller
@RequestMapping("/api/csv")
public class CSVController {
	
	@Autowired
	CSVService fileService;
	
	@PostMapping("/carica/provincia")
	public ResponseEntity<String> caricaFile(@RequestParam("File") MultipartFile file) {
		String message = "";
		
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);
				
				message = "Caricato file con successo: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			catch (Exception e) {
				message = "Caricamento file fallito: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		message = "Carica un file";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	

	@PostMapping("/carica/comune")
	public ResponseEntity<String> caricaFileComuni(@RequestParam("File") MultipartFile file) {
		String message = "";
		
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.saveComuni(file);
				
				message = "Caricato file con successo: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			}
			catch (Exception e) {
				message = "Caricamento file fallito: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		message = "Carica un file";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	  @GetMapping("/province")
	  public ResponseEntity<List<Provincia>> getAllTutorials() {
	    try {
	      List<Provincia> province = fileService.getAll();

	      if (province.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(province, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
