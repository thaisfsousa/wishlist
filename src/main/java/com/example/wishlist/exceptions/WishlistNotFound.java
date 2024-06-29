package com.example.wishlist.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WishlistNotFound extends WishlistException{

    public WishlistNotFound(){
        super("Wishlist not found.", HttpStatus.NOT_FOUND);
    }
}
