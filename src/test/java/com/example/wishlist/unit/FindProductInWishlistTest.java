package com.example.wishlist.unit;

import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.DTO.ProductResponseDTO;
import com.example.wishlist.core.exceptions.WishlistNotFound;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.FindProductInWishlist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.wishlist.repository.WishlistRepository;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindProductInWishlistTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private FindProductInWishlist findProductInWishlist;

    @Test
    void findProductByIdThrowsWishlistNotFound() {
        String customerId = "0";

        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFound.class, () -> findProductInWishlist.findProductById(customerId, "1"));
    }

    @Test
    void findProductByIdWhenProductExistsInWishlist() {
        ProductResponseDTO product = ProductDTOMock.create();
        Wishlist wishlist = new Wishlist("0", product);

        when(wishlistRepository.findByCustomerId("0")).thenReturn(Optional.of(wishlist));
        Boolean found = findProductInWishlist.findProductById("0", "1");

        assertTrue(found);
    }

    @Test
    void findProductByIdWhenProductDoesNotExistInWishlist() {
        String customerId = "0";
        Wishlist wishlist = new Wishlist(customerId);

        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
        Boolean found = findProductInWishlist.findProductById(customerId, "1");

        assertFalse(found);
    }
}
