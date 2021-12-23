package stock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stock.entities.dto.ProductDTO;
import stock.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	ResponseEntity<Page<ProductDTO>> listAllPaged(Pageable pageable) {
		Page<ProductDTO> dto = service.listAll(pageable);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/{name}")
	ResponseEntity<ProductDTO> findByName(@PathVariable String name) {
		ProductDTO dto = service.findByName(name);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/brand/{brand}")
	ResponseEntity<List<ProductDTO>> findByBrand(@PathVariable String brand) {
		List<ProductDTO> dto = service.findByBrand(brand);
		return ResponseEntity.ok().body(dto);
	}

}
