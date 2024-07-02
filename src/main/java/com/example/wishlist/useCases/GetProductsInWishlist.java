package com.example.wishlist.useCases;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductsInWishlist {

    private final WishlistRepository wishlistRepository;

    public final List<Product> findAll(String customerId) {
        Optional<Wishlist> wishlist = wishlistRepository.findByCustomerId(customerId);
        return wishlist.isEmpty() ? List.of() : wishlist.get().getProducts();
    }
}
