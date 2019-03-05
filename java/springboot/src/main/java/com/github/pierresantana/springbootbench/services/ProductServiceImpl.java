package com.github.pierresantana.springbootbench.services;

import com.github.pierresantana.springbootbench.exceptions.NotFoundException;
import com.github.pierresantana.springbootbench.model.Product;
import com.github.pierresantana.springbootbench.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> getProducts() {
        Set<Product> list = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id );
        }

        return product.get();
    }

    @Override
    public Product saveProduct(Product entity) {
        Product product = Product.Builder.from(entity).build();

        return productRepository.save(product);
    }

    @Override
    public void deleteById(String idToDelete) {
        productRepository.deleteById(idToDelete);
    }

}
