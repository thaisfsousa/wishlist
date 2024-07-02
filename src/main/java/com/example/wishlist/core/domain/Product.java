package com.example.wishlist.core.domain;

import com.example.wishlist.core.DTO.ProductResponseDTO;
import lombok.*;

@Data
public class Product {

    private String id;
    private String name;
    private Double price;

    public Product(){}

    public Product(final ProductResponseDTO product){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }
}
