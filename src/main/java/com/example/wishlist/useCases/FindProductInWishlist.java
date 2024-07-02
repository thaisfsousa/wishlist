package com.example.wishlist.useCases;

import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.WishlistNotFound;
import com.example.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProductInWishlist{

    private final WishlistRepository wishlistRepository;

    public Boolean findProductById(String customerId, String productId) {
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId).orElseThrow(WishlistNotFound::new);
        return wishlist.productExists(productId);
    }
}
