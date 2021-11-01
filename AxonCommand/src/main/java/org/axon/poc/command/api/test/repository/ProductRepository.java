package org.axon.poc.command.api.test.repository;

import org.axon.poc.command.api.test.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
