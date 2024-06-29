package com.example.wishlist.gateways.database.documents;

import com.example.wishlist.domain.Product;
import lombok.Data;

@Data
public class ProductDocument {
    private String productId;
    private String productName;
    private Double productPrice;

    public ProductDocument(Product product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
    }

    public ProductDocument(){}
}
