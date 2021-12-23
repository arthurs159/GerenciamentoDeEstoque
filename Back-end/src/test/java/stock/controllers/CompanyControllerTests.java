package stock.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions resultado =
				mockMvc.perform(get("/company")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$[0].name").value("Mercado Ponto Certo"));
		
	}
	
	@Test
	public void findByIdShouldReturnTheCompanyWithThisId() throws Exception {
		
		Long existingId = 1L;
		
		ResultActions resultado = 
				mockMvc.perform(get("/companies/{id}", existingId));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$.id").value(existingId));
		resultado.andExpect(jsonPath("$.name").isNotEmpty());
		resultado.andExpect(jsonPath("$.password").isNotEmpty());
		resultado.andExpect(jsonPath("$.cnpj").isNotEmpty());
	}
	

}
