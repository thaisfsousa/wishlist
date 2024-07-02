package com.example.wishlist.core.domain;

import com.example.wishlist.core.DTO.ProductResponseDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Document
public class Wishlist {

    @Id
    private String customerId;
    private List<Product> products;

    public Wishlist(){}

    public Wishlist(String customerId, ProductResponseDTO product){
        this.setCustomerId(customerId);
        this.setProducts(new ArrayList<>());
        this.getProducts().add(new Product(product));
    }

    public Boolean productExists(String productId){
        return this.getProducts().stream().anyMatch(prod -> prod.getId().equals(productId));
    }

    public Wishlist(String customerId){
        this.setCustomerId(customerId);
        this.setProducts(new ArrayList<>());
    }

    public void addOrUpdateWishlist(final ProductResponseDTO product){
        int productIndex = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(product.getId()))
                .findFirst().orElse(-1);
        if (productIndex != -1){
            this.getProducts().set(productIndex, new Product(product));
        }
        else
            this.getProducts().add(new Product(product));
    }
}
