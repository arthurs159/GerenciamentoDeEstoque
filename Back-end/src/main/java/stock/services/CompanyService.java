package stock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import stock.entities.Company;
import stock.entities.dto.CompanyDTO;
import stock.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	public List<CompanyDTO> listCompany() {
		List<Company> company = repository.findAll();
		return company.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CompanyDTO findById(Long id) {
		Optional<Company> company = repository.findById(id);
		
		Company entity = company.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada!!"));
		return new CompanyDTO(entity);
	}
}
