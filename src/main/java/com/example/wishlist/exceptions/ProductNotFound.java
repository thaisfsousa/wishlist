package com.example.wishlist.exceptions;

import com.example.wishlist.domain.Wishlist;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductNotFound extends WishlistException {

    public ProductNotFound() {
        super("Product not Found.", HttpStatus.NOT_FOUND);
    }
}