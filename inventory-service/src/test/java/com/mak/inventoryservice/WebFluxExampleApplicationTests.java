/*
package com.mak.inventoryservice;

import com.mak.inventoryservice.controller.ProductController;
import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.UUID;
//@AutoConfigureWebTestClient
*/
/*@ContextConfiguration(classes = {
		ProductController.class,
		ProductRepository.class
})*//*


*/
/** this example test for  Test REST APIs using Spring 5's Reactive
 * You need other dependencies webflux, reactor-test
 * https://github.com/callicoder/spring-webclient-webtestclient-demo
 *//*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"spring.cloud.discovery.enabled = false"})
*/
/*@ExtendWith(SpringExtension.class)*//*

@WebFluxTest(ProductController.class) //or
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebFluxExampleApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	WebTestClient webTestClient;


	@Test
	@Order(1)
	public void testAddProduct() {
		UUID uuid= UUID.randomUUID();
		Product product = new Product(null, "Smartphone", 7600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1));

		webTestClient.post().uri("/api/custom/products")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(product), Product.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.name").isEqualTo("Smartphone");

	}


	@Test
	@Order(2)
	public void testGetAllProducts() {
		webTestClient.get().uri("/api/custom/products")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Product.class);
	}

	@Test
	@Autowired
	public void testGetAllProducts2( ProductController productController) {

		WebTestClient.bindToController(productController)
				.build()
				.get()
				.uri("/api/custom/products")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Product.class);

		*/
/*//*
/ or you can use
		WebTestClient testClient = WebTestClient
				.bindToServer()
				.baseUrl("http://localhost:8080")
				.build();*//*

	}

	@Test
	public void testDeleteProduct() {
		Product buzzProduct = productRepository.save(new Product(null, "Smartphone", 7600, 34, false,
				LocalDate.of(2022, Month.DECEMBER, 2)));

		webTestClient.delete()
				.uri("/api/custom/products/{id}", Collections.singletonMap("id", buzzProduct.getId()))
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void contextLoads() {
	}

}*/
