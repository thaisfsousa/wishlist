package com.example.wishlist.gateways.database.documents;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Document
@Data
public class WishlistDocument {
    @Id
    private String customerId;
    private List<ProductDocument> products = Collections.emptyList();

    public WishlistDocument(Wishlist wishlist){
        this.setCustomerId(wishlist.getCustomerId());
        this.setProducts(wishlist.getProducts().stream().map(ProductDocument::new).collect(Collectors.toList()));
    }

    public WishlistDocument(){}
}
