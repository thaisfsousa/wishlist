package com.example.wishlist.gateways.database;

import com.example.wishlist.gateways.database.documents.WishlistDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistDocument, String> {
    Optional<WishlistDocument> findByCustomerId(String customerId);
}
