package com.mak.inventoryservice.controller;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.exception.ProductNotFoundException;
import com.mak.inventoryservice.repository.ProductRepository;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins= {"http://localhost:4200/"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Api(value = "API pour es opérations CRUD sur les produits.")
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
    Product saveProduct(@Valid @RequestBody Product p) {
        return productRepository.save(p);
    }


    /*  get all products  */
    @ApiOperation(value = "getProducts", notes = "", nickname = "Inventory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval", response = Product.class, responseContainer = "List"),
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Server error"),
    })
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@ApiParam(value = "name",
            required = false, defaultValue = "") @RequestParam(required = false) String name) {
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product productDTO) {

        Product productToSave = productRepository
                .save(new Product(null, productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), false, null, null, null));
        return new ResponseEntity<>(productToSave, HttpStatus.CREATED);
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
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
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