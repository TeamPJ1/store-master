package com.mak.inventoryservice.controller;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins= {"http://localhost:4200/"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RestController
@RequestMapping("/api/custom")
public class ProductController {

    private ProductRepository productRepository;

    // Injection with constructor, it's recommanded instead @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/selectedProducts")
    Collection<Product> getSelectedProducts() {
        return productRepository.findAll().stream().filter(p -> p.isSelected()).collect(Collectors.toList());
    }

    @PostMapping("/saveProduct")
    Product saveProduct(@RequestBody Product p) {
        return productRepository.save(p);
    }


    /*  Custom product  */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<Product> products = new ArrayList<Product>();
            if (name == null) {
                productRepository.findAll().forEach(products::add);
            } else {
                productRepository.findByNameContaining(name).forEach(products::add);
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product productDTO) {
        try {
            Product productToSave = productRepository
                    .save(new Product(null, productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), false));
            return new ResponseEntity<>(productToSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product productDTO) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Product productToUpdate = productData.get();
            productToUpdate.setName(productDTO.getName());
            productToUpdate.setPrice(productDTO.getPrice());
            productToUpdate.setQuantity(productDTO.getQuantity());
            productToUpdate.setSelected(productDTO.isSelected());
            return new ResponseEntity<>(productRepository.save(productDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/selected")
    public ResponseEntity<List<Product>> findBySelected() {
        try {
            List<Product> products = productRepository.findBySelected(true);
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}