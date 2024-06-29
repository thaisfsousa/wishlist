package com.example.wishlist.useCases;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.exceptions.WishlistNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProductInWishlist{

    private final WishlistGateway wishlistGateway;

    public Boolean findProductById(String customerId, String productId) {
        Wishlist wishlist = wishlistGateway.findByCustomerId(customerId).orElseThrow(WishlistNotFound::new);
        return wishlist.productExists(productId);
    }
}
