package it.epicode.be.data_loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.epicode.be.logic.ProvinciaService;
import it.epicode.be.persistence.ProvinciaRepo;

@Configuration
public class ConfigurationClass {

	@Bean
	public ProvinciaService getService() {

		return new ProvinciaService();
	}

}
