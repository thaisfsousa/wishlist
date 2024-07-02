package com.example.wishlist.unit;

import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.gateways.http.DTO.ProductResponseDTO;
import com.example.wishlist.exceptions.WishlistNotFound;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.FindProductInWishlist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindProductInWishlistTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private FindProductInWishlist findProductInWishlist;

    @Test
    void findProductByIdThrowsWishlistNotFound() {
        String customerId = "0";

        when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFound.class, () -> findProductInWishlist.findProductById(customerId, "1"));
    }

    @Test
    void findProductByIdWhenProductExistsInWishlist() {
        ProductResponseDTO product = ProductDTOMock.create();
        Wishlist wishlist = new Wishlist("0", product);

        when(wishlistGateway.findByCustomerId("0")).thenReturn(Optional.of(wishlist));
        Boolean found = findProductInWishlist.findProductById("0", "1");

        assertTrue(found);
    }

    @Test
    void findProductByIdWhenProductDoesNotExistInWishlist() {
        String customerId = "0";
        Wishlist wishlist = new Wishlist(customerId);

        when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
        Boolean found = findProductInWishlist.findProductById(customerId, "1");

        assertFalse(found);
    }
}
