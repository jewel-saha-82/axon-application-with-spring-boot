package org.axon.poc.query.api.repository;

import org.axon.poc.query.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
