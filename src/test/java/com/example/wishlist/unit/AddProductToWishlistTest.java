package com.example.wishlist.unit;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.WishlistExceedsLimit;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.AddProductToWishlist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddProductToWishlistTest {
    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private AddProductToWishlist addProductToWishlist;

    @Test
    void testSaveProduct() {

        Wishlist expectedWishlist = new Wishlist("0", ProductDTOMock.create());


        when(wishlistRepository.findByCustomerId("0")).thenReturn(Optional.of(new Wishlist("0")));
        when(wishlistRepository.save(any())).thenReturn(expectedWishlist);
        Wishlist savedWishlist = addProductToWishlist.saveProduct("0", ProductDTOMock.create());

        verify(wishlistRepository, times(1)).findByCustomerId("0");

        assertNotNull(savedWishlist);
        assertEquals("0", savedWishlist.getCustomerId());
        assertEquals(1, savedWishlist.getProducts().size());
        assertEquals("1", savedWishlist.getProducts().get(0).getId());
    }

    @Test
    void testAddProductThatAlreadyExistsInWishlist() {

        Wishlist existingWishlist = new Wishlist("1", ProductDTOMock.create("2"));

        when(wishlistRepository.findByCustomerId("1")).thenReturn(Optional.of(existingWishlist));
        when(wishlistRepository.save(any())).thenReturn(existingWishlist);
        Wishlist savedWishlist = addProductToWishlist.saveProduct("1", ProductDTOMock.create("2"));

        assertNotNull(savedWishlist);
        assertEquals("1", savedWishlist.getCustomerId());
        assertEquals(1, savedWishlist.getProducts().size());
        assertEquals("2", savedWishlist.getProducts().get(0).getId());
    }

    @Test
    void testWishlistExceedsLimit() {

        Wishlist exceedingWishlist = new Wishlist("3");
        List<Product> products = ProductDTOMock.createList(20);
        exceedingWishlist.setProducts(products);

        when(wishlistRepository.findByCustomerId("3")).thenReturn(Optional.of(exceedingWishlist));

        assertThrows(WishlistExceedsLimit.class, () -> addProductToWishlist.saveProduct("3", ProductDTOMock.create("100")));
        verify(wishlistRepository, times(1)).findByCustomerId("3");
        verify(wishlistRepository, never()).save(ArgumentMatchers.any(Wishlist.class));
    }
}
