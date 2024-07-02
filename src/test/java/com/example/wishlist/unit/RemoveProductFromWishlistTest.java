package com.example.wishlist.unit;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.WishlistNotFound;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.RemoveProductFromWishlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveProductFromWishlistTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private RemoveProductFromWishlist removeProductFromWishlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteWhenProductExists() {
        Wishlist wishlist = new Wishlist("0");
        List<Product> products = ProductDTOMock.createList(2);
        wishlist.setProducts(products);

        when(wishlistRepository.findByCustomerId("0")).thenReturn(Optional.of(wishlist));
        when(wishlistRepository.save(wishlist)).thenReturn(wishlist);

        removeProductFromWishlist.deleteProduct("0", "1");
        verify(wishlistRepository, times(1)).findByCustomerId("0");
        verify(wishlistRepository, times(1)).save(wishlist);

        assertEquals(1, wishlist.getProducts().size());
        assertFalse(wishlist.getProducts().stream().anyMatch(p -> p.getId().equals("1")));
    }

    @Test
    void testDeleteWhenWishlistNotFound() {
        String customerId = "1";

        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFound.class, () -> removeProductFromWishlist.deleteProduct(customerId, "1"));
    }
}

