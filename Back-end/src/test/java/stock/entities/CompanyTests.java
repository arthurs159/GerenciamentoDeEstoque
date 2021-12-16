package stock.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CompanyTests {
	
	@Test
	public void CompanyShouldHaveCorrectStructure() {
	
		Company company = new Company();
		company.setId(1L);
		company.setName("June Calçados");
		company.setPassword("123456");
		company.setCnpj("18088250000190");
		
		Assertions.assertNotNull(company.getId());
		Assertions.assertNotNull(company.getName());
		Assertions.assertNotNull(company.getPassword());
		Assertions.assertNotNull(company.getCnpj());
		
	}
}
