package stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stock.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
