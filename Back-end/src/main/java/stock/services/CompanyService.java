package stock.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
