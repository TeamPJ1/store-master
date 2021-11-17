package com.mak.inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mak.inventoryservice.controller.ProductController;
import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ProductController.class) // for unit test (test juste une classe)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class ProductRestControllerLayerUsingMockitoUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ProductRepository productRepository;

	/*@InjectMocks
	ProductController productController;*/

    @Test
    @Order(1)
    public void testAddProduct() throws Exception {
        Product newProduct = new Product(null, "Smartphone", 7600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1), LocalDateTime.now(), LocalDateTime.now());
        Product savedProduct = new Product(1L, "Smartphone", 7600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1),LocalDateTime.now(), LocalDateTime.now());

        // simulate behavior of product repository
        Mockito.when(productRepository.save(newProduct)).thenReturn(savedProduct);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/custom/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                //   .andExpect(jsonPath("$.id", is(savedProduct.getId())))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(savedProduct.getName())))
                .andExpect(jsonPath("$.price", is(savedProduct.getPrice())))
                .andExpect(jsonPath("$.quantity", is(savedProduct.getQuantity())))
                .andReturn();
        // check productRepository.save() has called
        Mockito.verify(productRepository, Mockito.times(1)).save(newProduct);

        // other way  to test
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        String exceptedJsonResponse = objectMapper.writeValueAsString(savedProduct);
        Assertions.assertThat(jsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

    }

   /* test input validation*/
    @Test
    @Order(1)
    public void testProductNameMustNotBeBlank() throws Exception {
        Product newProduct = new Product(null, "", 7600, 34, false, LocalDate.of(2022, Month.DECEMBER, 1),LocalDateTime.now(), LocalDateTime.now());

         this.mockMvc.perform(MockMvcRequestBuilders.post("/api/custom/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct))
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
         // check productRepository.save()  didn't called
        Mockito.verify(productRepository, Mockito.times(0)).save(newProduct);

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

        Mockito.when(productRepository.findAll()).thenReturn(productsList);


        MvcResult mvcResult = mockMvc.perform(get("/api/custom/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(productsList.get(0).getName())))
                .andExpect(jsonPath("$[0].price", is(productsList.get(0).getPrice())))
                .andExpect(jsonPath("$[0].quantity", is(productsList.get(0).getQuantity())))
                .andReturn();
        // other way  to test
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        String exceptedJsonResponse = objectMapper.writeValueAsString(productsList);
        Assertions.assertThat(jsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);


    }

    @Test
    public void testDeleteProduct() throws Exception {
        final long productId = 1L;
        Mockito.doNothing().when(productRepository).deleteById(productId);
        // Mockito.doThrow(new RuntimeException()).when(productRepository).deleteById(any());
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/custom/products/" + productId)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
        // check productRepository has called
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId);
    }

    @Test
    void contextLoads() {
    }

}