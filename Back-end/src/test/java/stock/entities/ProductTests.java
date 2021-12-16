package stock.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTests {

	@Test
	void CompanyShouldHaveCorrectStructure() {
	
		Product product = new Product();
		product.setId(1L);
		product.setName("Pomarola 250ml");
		product.setQuantity(12);
		product.setBrand("Pomarola");
		
		Assertions.assertNotNull(product.getId());
		Assertions.assertNotNull(product.getName());
		Assertions.assertNotNull(product.getQuantity());
		Assertions.assertNotNull(product.getBrand());
		
	}
}
