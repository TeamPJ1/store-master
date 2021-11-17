package com.mak.inventoryservice;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/*@ExtendWith(MockitoExtension.class): enabling the mockito extension .
@DataJpaTest: Annotation that will prepare spring data JPA. It will enable entity-based save, fetch, and other environments.
@BeforeEach: Here before execution started, we can initialize some tasks. Here, we are saving a default customer.
@AfterEach: AFter execution of all cases, we are doing some tasks here. Here we are removing all our changed data.*/
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@EnableJpaAuditing
public class ProductRepositoryLayerTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void initUseCase() {
        List<Product> products = Arrays.asList(
                 new Product("default", 600, 20, false, LocalDate.of(2022, Month.DECEMBER, 1))
        );
        productRepository.saveAll(products);
    }

    @AfterEach
    public void destroyAll(){
        productRepository.deleteAll();
    }

    @Test
    void saveAllSuccess() {
        List<Product> customers = Arrays.asList(
                new Product( "Smartphone", 400, 20, false, null),
                new Product( "Printer", 300, 20, false, null),
                new Product("Computer", 500, 20, false, LocalDate.of(2022, Month.DECEMBER, 1))
        );
        Iterable<Product> allProducts = productRepository.saveAll(customers);

        // use of AtomicInteger to check if all elements in streams has processed
        AtomicInteger validIdFound = new AtomicInteger();
        allProducts.forEach(customer -> {
            if(customer.getId()>0){
                validIdFound.getAndIncrement();
            }
        });
        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void findAllSuccess() {
        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts.size()).isGreaterThanOrEqualTo(1);
    }
}
