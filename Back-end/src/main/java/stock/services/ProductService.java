package stock.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stock.entities.Product;
import stock.entities.dto.ProductDTO;
import stock.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> listAll(Pageable pageable) {
		Page<Product> page = repository.findAll(pageable);
		return page.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findByName(String name) {
		Optional<Product> product = repository.findByName(name);
		Product entity = product.orElseThrow();
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product prod = new Product();
		toDto(dto, prod);
		prod = repository.save(prod);
		return new ProductDTO(prod);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product prod = repository.getOne(id);
		toDto(dto, prod);
		prod  = repository.save(prod);
		return new ProductDTO(prod);
	}
	
	
	private void toDto(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		entity.setBrand(dto.getBrand());
	}
}
