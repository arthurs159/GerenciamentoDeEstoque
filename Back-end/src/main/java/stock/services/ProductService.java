package stock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stock.entities.Product;
import stock.entities.dto.ProductDTO;
import stock.exceptions.ProductNotFoundException;
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
	public ProductDTO findByName(Long id) {
		Optional<Product> product = repository.findById(id);
		Product entity = product.orElseThrow();
		return new ProductDTO(entity);
	}

	@Transactional(readOnly = true)
	public ProductDTO findByName(String name) {
		Optional<Product> product = repository.findByName(name);
		Product entity = product.orElseThrow();
		return new ProductDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findByBrand(String name){
		List<Product> product = repository.findByBrand(name);
		return  product.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
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
		prod = repository.save(prod);
		return new ProductDTO(prod);
	}

	public ProductDTO addProduct(Long id, int quantity) throws ProductNotFoundException {
		Product addProduct = repository.getOne(id);

		int quantityToAdd = quantity + addProduct.getQuantity();
		addProduct.setQuantity(quantityToAdd);

		Product productAdded = repository.save(addProduct);
		return new ProductDTO(productAdded);
	}

	public ProductDTO RemoveProduct(Long id, int quantity) throws ProductNotFoundException {
		Product removeProduct = repository.getOne(id);

		int quantityToRemove = quantity - removeProduct.getQuantity();

		if (quantityToRemove > 0) {
			removeProduct.setQuantity(quantityToRemove);
			Product productAdded = repository.save(removeProduct);
			return new ProductDTO(productAdded);
		}
		throw new ProductNotFoundException("O Produto não pode ter quantidade negativa");
	}
	
	private void toDto(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		entity.setBrand(dto.getBrand());
	}

}
