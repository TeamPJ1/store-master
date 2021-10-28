package com.mak.inventoryservice;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.entities.Supplier;
import com.mak.inventoryservice.repository.ProductRepository;
import com.mak.inventoryservice.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            // Expose Id of objects
            restConfiguration.exposeIdsFor(Product.class);

            productRepository.save(new Product(null, "Computer", 7600, 34, false));
            productRepository.save(new Product(null, "Printer", 1600, 134, false));
            productRepository.save(new Product(null, "Smartphone", 1600, 34, false));
            productRepository.findAll().forEach(System.out::println);

            restConfiguration.exposeIdsFor(Supplier.class);

            Stream.of("JBOSS", "ORACLE", "IBM").forEach(
                    n -> {
                        supplierRepository.save(new Supplier(null, n, n + "@" + n.toLowerCase() + ".com"));
                    });
            supplierRepository.findAll().forEach(System.out::println);
        };
    }

}
