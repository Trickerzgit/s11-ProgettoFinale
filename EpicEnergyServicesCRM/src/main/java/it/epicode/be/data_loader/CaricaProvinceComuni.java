package it.epicode.be.data_loader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.epicode.be.logic.ProvinciaService;
import it.epicode.be.model.Provincia;

public class CaricaProvinceComuni {

	
	private static AnnotationConfigApplicationContext ctx;
	
	public static void main(String[] args) throws IOException {

//		letturaCSV("csv/province-italiane.csv");
		
		ctx = new AnnotationConfigApplicationContext(ConfigurationClass.class);
		
		ctx.refresh();
		
		ProvinciaService pService = ctx.getBean(ProvinciaService.class);
		
		List<Provincia> lista = creazioneLista("csv/province-italiane.csv");
		for (Provincia p : lista) {
			pService.saveProvincia(p);
		}
		
	}
	
	public static void letturaCSV(String input) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(input))) {
			try (CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withTrim())) {
				for (CSVRecord record : parser) {
					Provincia p = new Provincia();
					p.setSigla(record.get(0));
					p.setNome(record.get(1));
					p.setRegione(record.get(2));
					System.out.println(p);
				}

			}
		}
	}

	public static List<Provincia> creazioneLista(String input) throws IOException {
		List<Provincia> lista = new ArrayList<Provincia>();
		try (Reader reader = Files.newBufferedReader(Paths.get(input))) {
			try (CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withTrim())) {
				for (CSVRecord record : parser) {
					Provincia p = new Provincia();
					p.setSigla(record.get(0));
					p.setNome(record.get(1));
					p.setRegione(record.get(2));
					lista.add(p);

				}
			}
			return lista;
		}

	}

}
