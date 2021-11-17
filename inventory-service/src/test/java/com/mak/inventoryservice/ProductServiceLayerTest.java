package com.mak.inventoryservice;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import com.mak.inventoryservice.services.ProductService;
import com.mak.inventoryservice.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceLayerTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void initUseCase() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void savedProductSuccess() {
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(20);
        product.setQuantity(20);

        // providing knowledge
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    public void productExistsInDBSuccess() {
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(20);
        product.setQuantity(20);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        // providing knowledge
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> fetchedProducts = productService.fetchAllProducts();
        assertThat(fetchedProducts.size()).isGreaterThan(0);
    }

}