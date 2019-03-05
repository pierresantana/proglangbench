package com.github.pierresantana.springbootbench.controllers;

import com.github.pierresantana.springbootbench.dtos.ProductDto;
import com.github.pierresantana.springbootbench.model.Product;
import com.github.pierresantana.springbootbench.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductDto.RepresentationBuilder productRB;

    public ProductController(ProductService productService, ProductDto.RepresentationBuilder productRB) {
        this.productService = productService;
        this.productRB = productRB;
    }

    @GetMapping("products")
    public ResponseEntity<Set<ProductDto>> getProducts() {
        Set<ProductDto> products = productService.getProducts().stream()
                .map(productRB::toRepresentation)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(products);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        Product product = productService.findById(id);
        ProductDto productDto = productRB.toRepresentation(product);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("products")
    public ResponseEntity addProduct(@RequestBody ProductDto representation) {
        Product fromRepresentation = productRB.fromRepresentation(representation, Product.Builder.create());
        Product savedProduct = productService.saveProduct(fromRepresentation);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/products/{id}").build()
                .expand(savedProduct.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, ProductDto representation) {
        Product product = productService.findById(id);
        Product.Builder builder = Product.Builder.from(product);
        Product fromRepresentation = productRB.fromRepresentation(representation, builder);
        Product savedProduct = productService.saveProduct(fromRepresentation);
        ProductDto productDto = productRB.toRepresentation(savedProduct);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
