package com.mak.inventoryservice.repository;

import com.mak.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@CrossOrigin(originPatterns = "**", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//@CrossOrigin(origins = "http://localhost:4200")
// Java: Spring Data REST Repository
//@RepositoryRestResource(collectionResourceRel = "products", path = "products")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findBySelected(boolean selected);
    long countAllByName(String name);
}
