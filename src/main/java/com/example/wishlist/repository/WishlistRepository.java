package com.example.wishlist.repository;

import com.example.wishlist.core.domain.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    Optional<Wishlist> findByCustomerId(String customerId);
}
