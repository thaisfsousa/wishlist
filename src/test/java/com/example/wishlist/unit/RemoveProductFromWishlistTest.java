package com.example.wishlist.unit;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.exceptions.WishlistNotFound;
import com.example.wishlist.unit.mocks.ProductDTOMock;
import com.example.wishlist.useCases.RemoveProductFromWishlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveProductFromWishlistTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private RemoveProductFromWishlist removeProductFromWishlist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteWhenProductExists() {
        Wishlist wishlist = new Wishlist("0");
        List<Product> products = ProductDTOMock.createProductList(2).stream().map(Product::new).collect(Collectors.toList());
        wishlist.setProducts(products);

        when(wishlistGateway.findByCustomerId("0")).thenReturn(Optional.of(wishlist));
        when(wishlistGateway.save(wishlist)).thenReturn(wishlist);

        removeProductFromWishlist.deleteProduct("0", "1");
        verify(wishlistGateway, times(1)).findByCustomerId("0");
        verify(wishlistGateway, times(1)).save(wishlist);

        assertEquals(1, wishlist.getProducts().size());
        assertFalse(wishlist.getProducts().stream().anyMatch(p -> p.getProductId().equals("1")));
    }

    @Test
    void testDeleteWhenWishlistNotFound() {
        String customerId = "1";

        when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFound.class, () -> removeProductFromWishlist.deleteProduct(customerId, "1"));
    }
}

