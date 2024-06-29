package com.example.wishlist.domain;

import com.example.wishlist.gateways.database.documents.WishlistDocument;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Wishlist {

    private String id;
    private String customerId;
    private List<Product> products;

    public Wishlist(){}

    public Wishlist(String customerId, ProductDTO product){
        this.setCustomerId(customerId);
        this.setProducts(new ArrayList<>());
        this.getProducts().add(new Product(product));
    }

    public Wishlist(WishlistDocument wishlist){
        this.setCustomerId(wishlist.getCustomerId());
        this.setProducts(wishlist.getProducts().stream().map(Product::new).collect(Collectors.toList()));
    }

    public Boolean productExists(String productId){
        return this.getProducts().stream().anyMatch(prod -> prod.getProductId().equals(productId));
    }

    public Wishlist(String customerId){
        this.setCustomerId(customerId);
        this.setProducts(new ArrayList<>());
    }
}
