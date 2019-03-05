package com.github.pierresantana.springbootbench.dtos;

import com.github.pierresantana.springbootbench.model.BaseBuilder;
import com.github.pierresantana.springbootbench.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDto {

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

    public static class Builder implements BaseBuilder<ProductDto> {

        private ProductDto entity;

        public Builder(ProductDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ProductDto());
        }

        public static Builder from(ProductDto entity) {
            return new Builder(entity);
        }

        public Builder id(String id) {
            this.entity.setId(id);
            return this;
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
        public ProductDto build() {
            return this.entity;
        }
    }

    @Component
    public static class RepresentationBuilder implements BaseRepresentationBuilder<Product, ProductDto, Product.Builder> {

        @Override
        public ProductDto toRepresentation(Product entity) {
            return ProductDto.Builder.create()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .price(entity.getPrice())
                    .build();
        }

        @Override
        public Product fromRepresentation(ProductDto representation, Product.Builder builder) {
            return builder
                    .name(representation.getName())
                    .description(representation.getDescription())
                    .price(representation.getPrice())
                    .build();
        }
    }
}
