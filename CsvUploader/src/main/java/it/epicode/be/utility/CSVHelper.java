package it.epicode.be.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import it.epicode.be.persistence.ProvinciaRepo;

public class CSVHelper {

	public static String path = "text/csv";
//	static String[] Headers = {"Id", "Nome", "Regione", "Sigla"};

	public static boolean hasCSVFormat(MultipartFile file) {
		if (!path.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Provincia> csvToProvincia(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(';')
						.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Provincia> province = new ArrayList<Provincia>();

			Iterable<CSVRecord> csvRecords = parser.getRecords();

			for (CSVRecord record : csvRecords) {
				Provincia prov = new Provincia();
				prov.setNome(record.get(1));
				prov.setRegione(record.get(2));
				prov.setSigla(record.get(0));
				province.add(prov);
			}
			return province;
		} catch (IOException e) {
			throw new RuntimeException("Parsing CSV fallito: " + e.getMessage());
		}
	}

	public static List<Comune> csvToComune(InputStream is, List<Provincia> province) throws IOException {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(';')
						.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<Comune> comuni = new ArrayList<Comune>();

			Iterable<CSVRecord> records = parser.getRecords();

			for (CSVRecord record : records) {
				Comune comune = new Comune();
				comune.setNome(record.get(2));
				for (Provincia p : province) {
					
					if (p.getNome().equals(record.get(3))) {
						comune.setProvincia(p);
						
						comuni.add(comune);
					}
					else {
						System.out.println("Comune non creato: " + comune.getNome());
					}
				}

			}
			return comuni;

		}

		catch (IOException e) {
			throw new RuntimeException("Parsing CSV fallito: " + e.getMessage());
		}

	}

}
