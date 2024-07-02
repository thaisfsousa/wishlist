package com.example.wishlist.useCases;

import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.core.DTO.ProductResponseDTO;
import com.example.wishlist.core.exceptions.WishlistExceedsLimit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddProductToWishlist {

    private final WishlistRepository wishlistRepository;

    public Wishlist saveProduct(String customerId, ProductResponseDTO product) {
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId).orElse(new Wishlist(customerId));
        if (wishlist.getProducts().size() == 20)
            throw new WishlistExceedsLimit();
        wishlist.addOrUpdateWishlist(product);
        return wishlistRepository.save(wishlist);
    }
}
