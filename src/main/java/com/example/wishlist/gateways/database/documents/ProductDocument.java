package com.example.wishlist.gateways.database.documents;

import com.example.wishlist.domain.Product;
import lombok.Data;

@Data
public class ProductDocument {
    private String id;
    private String name;
    private Double price;

    public ProductDocument(Product product){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
    }

    public ProductDocument(){}
}
