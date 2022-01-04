package stock.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import stock.entities.dto.ProductDTO;
import stock.entities.dto.QuantityDTO;
import stock.exceptions.ProductNotFoundException;
import stock.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	ResponseEntity<List<ProductDTO>> listAll() {
		List<ProductDTO> dto = service.listAll();
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/{id}")
	ResponseEntity<ProductDTO> findByName(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/name/{name}")
	ResponseEntity<ProductDTO> findByName(@PathVariable String name) {
		ProductDTO dto = service.findByName(name);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/brand/{brand}")
	ResponseEntity<List<ProductDTO>> findByBrand(@PathVariable String brand) {
		List<ProductDTO> dto = service.findByBrand(brand);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<ProductDTO> delete (@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update (@PathVariable Long id, @RequestBody ProductDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@PatchMapping(value = "/{id}/add")
	public ProductDTO addProduct (@PathVariable Long id, @RequestBody QuantityDTO quantityDTO) throws ProductNotFoundException{
		return service.addProduct(id, quantityDTO.getQuantity());
	}
	
	@PatchMapping(value = "/{id}/remove")
	public ProductDTO removeProduct (@PathVariable Long id, @RequestBody QuantityDTO quantityDTO) throws ProductNotFoundException{
		return service.removeProduct(id, quantityDTO.getQuantity());
	}
	
}
