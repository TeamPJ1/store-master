package com.mak.inventoryservice.services;

import com.mak.inventoryservice.entities.Product;

import java.util.List;

public interface ProductService {
    boolean saveUpdateProduct(Product request);
    List<Product> findProductByContaining(String name);
    List<Product> fetchAllProducts() ;
}
