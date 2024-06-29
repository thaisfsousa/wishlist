package com.example.wishlist.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WishlistExceedsLimit extends WishlistException {

    public WishlistExceedsLimit() {
        super("Wishlist exceeds limit of Products.", HttpStatus.BAD_REQUEST);
    }
}
