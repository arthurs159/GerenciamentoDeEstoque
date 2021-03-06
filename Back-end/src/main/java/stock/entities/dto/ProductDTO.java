package stock.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stock.entities.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private int quantity;
	private String brand;
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		quantity = entity.getQuantity();
		brand = entity.getBrand();
	}
	
}
