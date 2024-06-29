package com.example.wishlist.useCases;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import com.example.wishlist.exceptions.WishlistExceedsLimit;
import com.example.wishlist.useCases.validator.MaxProductsValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddProductToWishlist {

    private final WishlistGateway wishlistGateway;

    public Wishlist saveProduct(String customerId, ProductDTO product) {
        Wishlist wishlist = wishlistGateway.findByCustomerId(customerId).orElse(new Wishlist(customerId));
        if (wishlist.productExists(product.getProductId()))
            return wishlist;
        if (MaxProductsValidator.exceedsProducts(wishlist.getProducts(), 20))
            throw new WishlistExceedsLimit();
        wishlist.getProducts().add(new Product(product));
        return wishlistGateway.save(wishlist);
    }
}
