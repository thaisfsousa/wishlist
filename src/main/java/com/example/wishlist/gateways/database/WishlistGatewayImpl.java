package com.example.wishlist.gateways.database;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.documents.WishlistDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WishlistGatewayImpl implements WishlistGateway{

    private final WishlistRepository wishlistRepository;

    public Optional<Wishlist> findByCustomerId(String customerId) {
        Optional<WishlistDocument> wishlistDocument = wishlistRepository.findByCustomerId(customerId);
        return wishlistDocument.map(Wishlist::new);
    }

    public Wishlist save(Wishlist wishlist){
        WishlistDocument wishlistDocument = wishlistRepository.save(new WishlistDocument(wishlist));
        return new Wishlist(wishlistDocument);
    };
}
