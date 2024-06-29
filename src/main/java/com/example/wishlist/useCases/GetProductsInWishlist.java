package com.example.wishlist.useCases;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductsInWishlist {

    private final WishlistGateway wishlistGateway;

    public final List<Product> findAll(String customerId) {
        Optional<Wishlist> wishlist = wishlistGateway.findByCustomerId(customerId);
        return wishlist.isEmpty() ? List.of() : wishlist.get().getProducts();
    }
}
