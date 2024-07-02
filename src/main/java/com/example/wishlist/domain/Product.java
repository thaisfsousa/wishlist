package com.example.wishlist.domain;

import com.example.wishlist.gateways.database.documents.ProductDocument;
import com.example.wishlist.gateways.rest.DTO.ProductResponseDTO;
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

    public Product(final ProductDocument product){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }

}
