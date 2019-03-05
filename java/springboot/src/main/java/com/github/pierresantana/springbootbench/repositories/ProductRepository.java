package com.github.pierresantana.springbootbench.repositories;

import com.github.pierresantana.springbootbench.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
