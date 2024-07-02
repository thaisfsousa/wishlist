package com.example.wishlist.gateways.http.DTO;


import com.example.wishlist.domain.Product;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductResponseDTO {

    @NotBlank(message = " - productId cannot be empty.")
    private String id;
    @NotBlank(message = " - productName cannot be empty.")
    private String name;
    private Double price;

    public ProductResponseDTO(final Product product){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }

    public ProductResponseDTO(String productId, String productName, Double productPrice){
        this.setId(productId);
        this.setName(productName);
        this.setPrice(productPrice);
    }

    public ProductResponseDTO(){}
}
