package com.example.wishlist.useCases;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.exceptions.ProductNotFound;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.exceptions.WishlistNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RemoveProductFromWishlist {

    private final WishlistGateway wishlistGateway;

    public void deleteProduct(String customerId, String productId) {
        Wishlist wishlist = wishlistGateway.findByCustomerId(customerId).orElseThrow(WishlistNotFound::new);
        if (!wishlist.productExists(productId))
            throw new ProductNotFound();
        wishlist.getProducts().removeIf(product -> product.getProductId().equals(productId));
        wishlistGateway.save(wishlist);
    }
}
