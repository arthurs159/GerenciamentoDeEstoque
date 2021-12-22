package stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stock.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
