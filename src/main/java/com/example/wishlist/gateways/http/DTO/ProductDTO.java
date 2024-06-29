package com.example.wishlist.gateways.http.DTO;


import com.example.wishlist.domain.Product;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProductDTO {

    @NotBlank(message = " - productId cannot be empty.")
    private String productId;
    @NotBlank(message = " - productName cannot be empty.")
    private String productName;
    private Double productPrice;

    public ProductDTO(final Product product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
    }

    public ProductDTO(String productId, String productName, Double productPrice){
        this.setProductId(productId);
        this.setProductName(productName);
        this.setProductPrice(productPrice);
    }

    public ProductDTO(){}
}
