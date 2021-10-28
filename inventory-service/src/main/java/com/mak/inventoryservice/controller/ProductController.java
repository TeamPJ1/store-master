package com.mak.inventoryservice.controller;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductRepository productRepository;

    // Injection with constructor, it's recommanded instead @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/selectedProducts")
    Collection<Product> getBill() {
        return productRepository.findAll().stream().filter(p -> p.isSelected()).collect(Collectors.toList());
    }



}