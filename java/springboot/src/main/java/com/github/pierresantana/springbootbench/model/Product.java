package com.github.pierresantana.springbootbench.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class Builder implements BaseBuilder<Product> {

        private Product entity;

        public Builder(Product entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new Product());
        }

        public static Builder from(Product entity) {
            return new Builder(entity);
        }

        public Builder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public Builder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public Builder price(BigDecimal price) {
            this.entity.setPrice(price);
            return this;
        }

        @Override
        public Product build() {
            return this.entity;
        }
    }
}