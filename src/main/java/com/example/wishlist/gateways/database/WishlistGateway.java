package com.example.wishlist.gateways.database;

import com.example.wishlist.domain.Wishlist;

import java.util.Optional;

public interface WishlistGateway {

    public Optional<Wishlist> findByCustomerId(String customerId);

    public Wishlist save(Wishlist wishlist);
}
