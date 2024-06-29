package com.example.wishlist.unit;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import com.example.wishlist.exceptions.WishlistExceedsLimit;
import com.example.wishlist.unit.mocks.ProductDTOMock;
import com.example.wishlist.useCases.AddProductToWishlist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddProductToWishlistTest {
    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private AddProductToWishlist addProductToWishlist;

    @Test
    void testSaveProduct() {

        Wishlist expectedWishlist = new Wishlist("0", ProductDTOMock.create());

        when(wishlistGateway.save(any())).thenReturn(expectedWishlist);
        Wishlist savedWishlist = addProductToWishlist.saveProduct("0", ProductDTOMock.create());

        verify(wishlistGateway, times(1)).findByCustomerId("0");
        verify(wishlistGateway, times(1)).save(any(Wishlist.class));
        assertNotNull(savedWishlist);
        assertEquals("0", savedWishlist.getCustomerId());
        assertEquals(1, savedWishlist.getProducts().size());
        assertEquals("1", savedWishlist.getProducts().get(0).getProductId());
    }

    @Test
    void testAddProductThatAlreadyExistsInWishlist() {

        Wishlist existingWishlist = new Wishlist("1", ProductDTOMock.create("2"));

        when(wishlistGateway.findByCustomerId("1")).thenReturn(Optional.of(existingWishlist));
        Wishlist savedWishlist = addProductToWishlist.saveProduct("1", ProductDTOMock.create("2"));

        assertNotNull(savedWishlist);
        verify(wishlistGateway, never()).save(any(Wishlist.class));
        assertEquals("1", savedWishlist.getCustomerId());
        assertEquals(1, savedWishlist.getProducts().size());
        assertEquals("2", savedWishlist.getProducts().get(0).getProductId());
    }

    @Test
    void testWishlistExceedsLimit() {

        Wishlist exceedingWishlist = new Wishlist("3");
        List<Product> products = ProductDTOMock.createProductList(20).stream().map(Product::new).toList();
        exceedingWishlist.setProducts(products);

        when(wishlistGateway.findByCustomerId("3")).thenReturn(Optional.of(exceedingWishlist));

        assertThrows(WishlistExceedsLimit.class, () -> addProductToWishlist.saveProduct("3", ProductDTOMock.create("100")));
        verify(wishlistGateway, times(1)).findByCustomerId("3");
        verify(wishlistGateway, never()).save(ArgumentMatchers.any(Wishlist.class));
    }
}
