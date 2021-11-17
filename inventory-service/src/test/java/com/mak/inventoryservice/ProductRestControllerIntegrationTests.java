package com.mak.inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mak.inventoryservice.controller.ProductController;
import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = ProductController.class) for unit test (test juste une classe)
/*@ContextConfiguration*/
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.cloud.discovery.enabled = false"})
public class ProductRestControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;

	/*@InjectMocks
	ProductController productController;*/

    @Test
    @Order(1)
    public void testAddProduct() throws Exception {
        Product newProduct = new Product(null, "Smartphone", 7600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1), LocalDateTime.now(), LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/custom/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(newProduct.getName())))
                .andExpect(jsonPath("$.price", is(newProduct.getPrice())))
                .andExpect(jsonPath("$.quantity", is(newProduct.getQuantity())));
    }

    @Test
    @Order(1)
    public void testAddProductNotBlank() throws Exception {
        Product newProduct = new Product(null, "", 0, 0, false, null, null, null);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/custom/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(newProduct.getName())))
                .andExpect(jsonPath("$.price", is(newProduct.getPrice())))
                .andExpect(jsonPath("$.quantity", is(newProduct.getQuantity())));
    }


    @Test
    @Order(2)
    public void testGetAllProducts() throws Exception {

        Stream<Product> productStream = Stream.of(
                new Product(null, "Computer", 7600, 34, false, LocalDate.of(2022, Month.JANUARY, 1),LocalDateTime.now(), LocalDateTime.now()),
                new Product(null, "Printer", 1600, 134, false, LocalDate.of(2022, Month.FEBRUARY, 1),LocalDateTime.now(), LocalDateTime.now()),
                new Product(null, "Smartphone", 1600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1),LocalDateTime.now(), LocalDateTime.now())
        );
        List<Product> productsList = productStream.collect(Collectors.toList());

        mockMvc.perform(get("/api/custom/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(productsList.get(0).getName())))
                .andExpect(jsonPath("$[0].price", is(productsList.get(0).getPrice())))
                .andExpect(jsonPath("$[0].quantity", is(productsList.get(0).getQuantity())));

    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = productRepository.save(new Product(null, "Smartphone", 7600, 34, false,
                LocalDate.of(2022, Month.DECEMBER, 2),LocalDateTime.now(), LocalDateTime.now()));
        Product savedProduct = productRepository.save(product);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/custom/products/" + savedProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void contextLoads() {
    }

}