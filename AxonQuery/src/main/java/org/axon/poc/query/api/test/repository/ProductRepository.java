package org.axon.poc.query.api.test.repository;

import org.axon.poc.query.api.test.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
