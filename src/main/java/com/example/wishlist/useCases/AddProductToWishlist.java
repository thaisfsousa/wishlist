package com.example.wishlist.useCases;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.gateways.http.DTO.ProductResponseDTO;
import com.example.wishlist.exceptions.WishlistExceedsLimit;
import com.example.wishlist.useCases.validator.MaxProductsValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddProductToWishlist {

    private final WishlistGateway wishlistGateway;

    public Wishlist saveProduct(String customerId, ProductResponseDTO product) {
        Wishlist wishlist = wishlistGateway.findByCustomerId(customerId).orElse(new Wishlist(customerId));
        if (MaxProductsValidator.exceedsProducts(wishlist.getProducts(), 20))
            throw new WishlistExceedsLimit();
        wishlist.addOrUpdateWishlist(product);
        return wishlistGateway.save(wishlist);
    }
}
