package stock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stock.entities.dto.CompanyDTO;
import stock.services.CompanyService;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@GetMapping
	ResponseEntity<List<CompanyDTO>> listCompany(){
		List<CompanyDTO> company = service.listCompany();
		return ResponseEntity.ok().body(company);
	}
	
	@GetMapping(value = "/{id}")
	ResponseEntity<CompanyDTO> findById(@PathVariable Long id){
		CompanyDTO company = service.findById(id);
		
		return ResponseEntity.ok().body(company);
	}
}
