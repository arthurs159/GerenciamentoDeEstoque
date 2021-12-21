package stock.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
				mockMvc.perform(get("/companies")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$[0].name").value("Bom Garoto"));
		resultado.andExpect(jsonPath("$[1].name").value("IndosFarm"));
		resultado.andExpect(jsonPath("$[2].name").value("Mercado Quero"));
		
		resultado.andExpect(jsonPath("&[0].password").value("12345"));
		resultado.andExpect(jsonPath("&[1].password").value("123456"));
		resultado.andExpect(jsonPath("&[2].password").value("1234567"));
		
		resultado.andExpect(jsonPath("&[0].cnpj").value("20061986000117"));
		resultado.andExpect(jsonPath("&[1].cnpj").value("77360719000168"));
		resultado.andExpect(jsonPath("&[2].cnpj").value("20177053000190"));
		
	}
	
	@Test
	public void findByIdShouldReturnTheCompanyWithThisId() throws Exception {
		
		Long existingId = 1L;
		
		ResultActions resultado = 
				mockMvc.perform(get("companies/{id}", existingId));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$.id").value(existingId));
		resultado.andExpect(jsonPath("$.name").isNotEmpty());
		resultado.andExpect(jsonPath("$.password").isNotEmpty());
		resultado.andExpect(jsonPath("$.cnpj").isNotEmpty());
		
	}
	
	@Test
	public void deleteShouldReturnoNoContentOnAnExistingId() throws Exception {
		
		Long existingId = 2L;
		
		ResultActions resultado =
				mockMvc.perform(delete("/companies/{id}", existingId));
		
		resultado.andExpect(status().isNoContent());
	}
	
	public void deleteShoulReturnNotFoundWhenIdNotExists() throws Exception {
		
		Long nonExistentId = 10L;
		
		ResultActions resultado = 
				mockMvc.perform(delete("/companies/{id}", nonExistentId));
		
		resultado.andExpect(status().isNotFound());
		
	}
	
	
		
}
