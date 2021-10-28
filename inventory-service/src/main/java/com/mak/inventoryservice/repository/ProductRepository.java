package com.mak.inventoryservice.repository;

import com.mak.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;



// Java: Spring Data REST Repository
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
//@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
