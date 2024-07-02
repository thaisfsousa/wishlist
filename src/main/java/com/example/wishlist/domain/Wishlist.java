package com.example.wishlist.domain;

import com.example.wishlist.gateways.database.documents.WishlistDocument;
import com.example.wishlist.gateways.rest.DTO.ProductResponseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Wishlist {

    private String customerId;
    private List<Product> products;

    public Wishlist(){}

    public Wishlist(String customerId, ProductResponseDTO product){
        this.setCustomerId(customerId);
        this.setProducts(new ArrayList<>());
        this.getProducts().add(new Product(product));
    }

    public Wishlist(WishlistDocument wishlist){
        this.setCustomerId(wishlist.getCustomerId());
        this.setProducts(wishlist.getProducts().stream().map(Product::new).collect(Collectors.toList()));
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
