package com.example.wishlist.domain;

import com.example.wishlist.gateways.database.documents.WishlistDocument;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
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

    public void addOrUpdateWishlist(final ProductDTO product){
        int productIndex = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getProductId().equals(product.getProductId()))
                .findFirst().orElse(-1);
        if (productIndex != -1){
            this.getProducts().set(productIndex, new Product(product));
        }
        else
            this.getProducts().add(new Product(product));
    }
}
