package com.example.wishlist.useCases;

import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.ProductNotFound;
import com.example.wishlist.core.exceptions.WishlistNotFound;
import com.example.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RemoveProductFromWishlist {

    private final WishlistRepository wishlistRepository;

    public void deleteProduct(String customerId, String productId) {
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId).orElseThrow(WishlistNotFound::new);
        if (!wishlist.productExists(productId))
            throw new ProductNotFound();
        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));
        wishlistRepository.save(wishlist);
    }
}
