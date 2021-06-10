package it.epicode.be;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import it.epicode.be.persistence.ClienteRepo;

@SpringBootTest
@AutoConfigureMockMvc
class EpicEnergyServicesApplicationTests {

	@Autowired
	private MockMvc mock;
	
	@Autowired 
	private ClienteRepo repoC;
	
	String key = "Authorization";
	String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsbyIsImlhdCI6MTYyMzMyMDI1MCwiZXhwIjoxNjIzNDA2NjUwfQ.S274Bzo-otHRhgk9-MSQlHiK6yswGGE_qNUhQNDWVMT-_-ilhB-oo3oaBiUuQKRv6Ryr8h1Ry6dbgewRv_tn_A";
	
	@Test
	public void testCreaCliente() throws Exception {
		String json = "{\n"
				+ "    \"ragioneSociale\" : \"Fiat\",\n"
				+ "    \"partitaIva\" : \"1234\",\n"
				+ "    \"email\" : \"Fiat@gmail.com\",\n"
				+ "    \"dataUltimoContatto\" : \"2021-10-10\",\n"
				+ "    \"fatturatoAnnuale\" : \"500\",\n"
				+ "    \"pec\" : \"Fiat@pec.it\",\n"
				+ "    \"telefono\" : \"3232323232\",\n"
				+ "    \"emailContatto\" : \"contattoFiat@gmail.com\",\n"
				+ "    \"nomeContatto\" : \"ContattoFiat\",\n"
				+ "    \"cognomeContatto\" : \"Macchina\",\n"
				+ "    \"telefonoContatto\" : \"34004405940\",\n"
				+ "    \"sedeLegale\" : {\n"
				+ "        \"via\" : \"Via delle auto\",\n"
				+ "        \"civico\" : \"12\",\n"
				+ "        \"localita\" : \"Roma\",\n"
				+ "        \"cap\" : \"00162\"\n"
				+ "    },\n"
				+ "    \"sedeOperativa\" : {\n"
				+ "        \"via\" : \"Via delle macchine\",\n"
				+ "        \"civico\" : \"10\",\n"
				+ "        \"localita\" : \"Roma\",\n"
				+ "        \"cap\" : \"00160\"\n"
				+ "    },\n"
				+ "    \"tipo\" : \"SAS\"\n"
				+ "}";
		
		MockHttpServletRequestBuilder azione = post("/api/cliente/inserisci")
											  .header(key, token)
											  .contentType(MediaType.APPLICATION_JSON)
											  .content(json)
											  .accept(MediaType.APPLICATION_JSON);
		
		mock.perform(azione)
			.andDo(print())
			.andExpect(status().isOk());
	}
	
//	@Test
//	public void testEliminaCliente() throws Exception {
//		mock.perform(delete("/api/cliente/elimina/2").header(key, token)).andDo(print()).andExpect(status().isOk());
//	}
	
	@Test
	public void testGetClienteById() throws Exception {
		String expected = "{\n"
				+ "    \"id\": 20,\n"
				+ "    \"ragioneSociale\": \"Terzo\",\n"
				+ "    \"partitaIva\": \"aaaaa\",\n"
				+ "    \"email\": \"Primo@gmail.com\",\n"
				+ "    \"dataInserimento\": \"2021-06-09T13:08:58.751+00:00\",\n"
				+ "    \"dataUltimoContatto\": null,\n"
				+ "    \"fatturatoAnnuale\": 15.00,\n"
				+ "    \"pec\": \"eeee@pec\",\n"
				+ "    \"telefono\": \"3232323232\",\n"
				+ "    \"emailContatto\": \"abab@gmail.com\",\n"
				+ "    \"nomeContatto\": \"principe\",\n"
				+ "    \"cognomeContatto\": \"acaso\",\n"
				+ "    \"telefonoContatto\": \"2323223\",\n"
				+ "    \"sedeLegale\": {\n"
				+ "        \"id\": 21,\n"
				+ "        \"via\": \"via acaso\",\n"
				+ "        \"civico\": 12,\n"
				+ "        \"localita\": \"Roma\",\n"
				+ "        \"cap\": 162,\n"
				+ "        \"comune\": null\n"
				+ "    },\n"
				+ "    \"sedeOperativa\": {\n"
				+ "        \"id\": 22,\n"
				+ "        \"via\": \"\",\n"
				+ "        \"civico\": 0,\n"
				+ "        \"localita\": \"\",\n"
				+ "        \"cap\": null,\n"
				+ "        \"comune\": null\n"
				+ "    },\n"
				+ "    \"fatture\": [],\n"
				+ "    \"tipo\": \"SAS\"\n"
				+ "}";
		
		mock.perform(get("/api/cliente/get/20").header(key, token))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(expected));
	}

}
