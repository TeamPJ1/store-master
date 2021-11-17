package com.mak.inventoryservice.services;

import com.mak.inventoryservice.entities.Product;
import com.mak.inventoryservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static Log log = LogFactory.getLog(ProductServiceImpl.class);

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean saveUpdateProduct(Product request) {
        Product product;

        if(productRepository.countAllByName(request.getName())>0){
            log.error("First name already exists");
            return false;
        }
        if (request.getId() == null || request.getId() == 0) {
            product = new Product();
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());
            product.setSelected(request.isSelected());
            product.setReleaseDate(request.getReleaseDate());
        } else {
            product = request;
        }
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

}