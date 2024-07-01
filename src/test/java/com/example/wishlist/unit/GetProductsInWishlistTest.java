package com.example.wishlist.unit;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.GetProductsInWishlist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductsInWishlistTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private GetProductsInWishlist getProductsInWishlist;

    @Test
    void findAllWhenWishlistNotFound() {
        String customerId = "3";

        when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.empty());
        List<Product> products = getProductsInWishlist.findAll(customerId);

        assertTrue(products.isEmpty());
        verify(wishlistGateway).findByCustomerId(customerId);
    }

    @Test
    void findAllWhenWishlistFound() {
        Wishlist wishlist = new Wishlist("0");
        List<Product> products = ProductDTOMock.createList(2);
        wishlist.setProducts(products);

        when(wishlistGateway.findByCustomerId("0")).thenReturn(Optional.of(wishlist));
        List<Product> returnedProducts = getProductsInWishlist.findAll("0");

        assertEquals(2, products.size());
        verify(wishlistGateway).findByCustomerId("0");
    }
}
