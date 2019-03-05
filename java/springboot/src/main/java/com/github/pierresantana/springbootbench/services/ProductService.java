package com.github.pierresantana.springbootbench.services;

import com.github.pierresantana.springbootbench.model.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> getProducts();

    Product findById(String id);

    Product saveProduct(Product product);

    void deleteById(String idToDelete);

}
