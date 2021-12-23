package stock.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import stock.entities.dto.ProductDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions resultado =
				mockMvc.perform(get("/products")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$[0].name").value("Barra de chocolate"));
		resultado.andExpect(jsonPath("$[1].name").value("Queijo coalho"));
		resultado.andExpect(jsonPath("$[2].name").value("Geleia de morango"));
		
	}
	
	@Test
	public void findByIdShouldReturnTheProductWithThisId() throws Exception {
		
		Long existingId = 1L;
		
		ResultActions resultado = 
				mockMvc.perform(get("/products/{id}", existingId));
		
		resultado.andExpect(status().isOk());
		resultado.andExpect(jsonPath("$.id").value(existingId));
		resultado.andExpect(jsonPath("$.name").isNotEmpty());
		resultado.andExpect(jsonPath("$.quantity").isNotEmpty());
		resultado.andExpect(jsonPath("$.brand").isNotEmpty());
	}
	
	@Test
	void insertShouldCreateNewProduct() throws Exception {
		ProductDTO dto = new ProductDTO(null, "Molho de tomate", 10, "Heinz");
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions resultado =
				mockMvc.perform(post("/products")
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		resultado.andExpect(status().isCreated());
		resultado.andExpect(jsonPath("$.id").exists());
		resultado.andExpect(jsonPath("$.name").exists());
		resultado.andExpect(jsonPath("$.quantity").exists());	
		resultado.andExpect(jsonPath("$.brand").exists());	
					
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenCompanyDoesNotExists() throws Exception {
		
		Long nonExistingId = 10L;
		
		ResultActions resultado =
				mockMvc.perform(get("/products/{id}", nonExistingId));
		
		resultado.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void deleteShouldReturnoNoContentOnAnExistingId() throws Exception {
		
		Long existingId = 2L;
		
		ResultActions resultado =
				mockMvc.perform(delete("/products/{id}", existingId));
		
		resultado.andExpect(status().isNoContent());
	}
	
	public void deleteShoulReturnNotFoundWhenIdNotExists() throws Exception {
		
		Long nonExistentId = 10L;
		
		ResultActions resultado = 
				mockMvc.perform(delete("/products/{id}", nonExistentId));
		
		resultado.andExpect(status().isNotFound());
		
	}
	
	
	
}
