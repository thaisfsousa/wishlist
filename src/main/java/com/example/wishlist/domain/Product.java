package com.example.wishlist.domain;

import com.example.wishlist.gateways.database.documents.ProductDocument;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import lombok.*;

@Data
public class Product {

    private String productId;
    private String productName;
    private Double productPrice;

    public Product(){}

    public Product(final ProductDTO product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
    }

    public Product(final ProductDocument product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
    }

}
