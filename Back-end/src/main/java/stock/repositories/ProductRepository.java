package stock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import stock.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByName(String name);
}
