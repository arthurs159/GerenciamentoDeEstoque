package stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import stock.entities.Product;
import stock.entities.dto.ProductDTO;
import stock.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Page<ProductDTO> listAll(Pageable pageable) {
		Page<Product> page = repository.findAll(pageable);
		return page.map(x -> new ProductDTO(x));
	}
	
	
}
