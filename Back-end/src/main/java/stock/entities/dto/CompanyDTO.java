package stock.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stock.entities.Company;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

	private Long id;
	private String name;
	private String password;
	private String cnpj;
	
	public CompanyDTO(Company entity) {
		id = entity.getId();
		name = entity.getName();
		password = entity.getPassword();
		cnpj = entity.getCnpj();
	}
}
