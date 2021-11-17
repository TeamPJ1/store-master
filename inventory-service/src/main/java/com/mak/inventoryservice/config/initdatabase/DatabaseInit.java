package com.mak.inventoryservice.config.initdatabase;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.entities.Supplier;
import com.mak.inventoryservice.repository.ProductRepository;
import com.mak.inventoryservice.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

@Configuration
public class DatabaseInit {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            // Expose Id of objects
            restConfiguration.exposeIdsFor(Product.class);
            Stream<Product> productStream = Stream.of(
                    new Product(null, "Computer", 7600, 34, false, LocalDate.of(2022, Month.JANUARY, 1),LocalDateTime.now(), LocalDateTime.now()),
                    new Product(null, "Printer", 1600, 134, false, LocalDate.of(2022, Month.FEBRUARY, 1),LocalDateTime.now(), LocalDateTime.now()),
                    new Product(null, "Smartphone", 1600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1),LocalDateTime.now(), LocalDateTime.now())
            );
            productStream.forEach(p -> productRepository.save(p));
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
