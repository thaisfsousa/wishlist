package com.example.wishlist.bdd;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.WishlistExceedsLimit;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.AddProductToWishlist;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductToWishlistSteps {
    private final WishlistRepository wishlistRepository = Mockito.mock(WishlistRepository.class);
    private Wishlist wishlist;
    private Exception exception;

    @Given("a wishlist for customer '$customerId' with $size products")
    public void givenAWishlistForCustomerWithProducts(String customerId, int size) {
        wishlist = new Wishlist(customerId);
        List<Product> products = ProductDTOMock.createList(size);
        wishlist.setProducts(products);

        Mockito.when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
    }

    @Given("a wishlist for customer '$customerId' with products including '$productId' and size $size")
    public void includingProduct(String customerId, String productId, int size) {
        wishlist = new Wishlist(customerId);
        List<Product> products = ProductDTOMock.createList(size);
        wishlist.setProducts(products);

        Mockito.when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
    }

    @Given("a wishlist for customer '$customerId' already has $size products")
    public void exceedsProductsLimit(String customerId, int size) {
        wishlist = new Wishlist(customerId);
        List<Product> products = ProductDTOMock.createList(size);
        wishlist.setProducts(products);

        Mockito.when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
    }

    @When("I try to add a new product with ID '$productId' to the wishlist")
    public void whenIAddANewProductToTheWishlist(String productId) {
        Wishlist newWishlist = new Wishlist(wishlist.getCustomerId());
        newWishlist.setProducts(wishlist.getProducts());
        if (!wishlist.productExists(productId) && wishlist.getProducts().size() < 20){
            newWishlist.getProducts().add(new Product(ProductDTOMock.create(productId)));
            Mockito.when(wishlistRepository.save(wishlist)).thenReturn(newWishlist);
        }
        try {
            new AddProductToWishlist(wishlistRepository).saveProduct(wishlist.getCustomerId(), ProductDTOMock.create(productId));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the product should be added with ID '$productId'")
    public void thenTheProductShouldBeAdded(String productId) {
        assertTrue(wishlist.productExists(productId));
    }

    @Then("The product list size should be $size")
    public void thenTheWishlistShouldContain(int size) {
        assertEquals(size, wishlist.getProducts().size());
    }

    @Then("I should be informed that the wishlist exceeds the limit")
    public void thenIShouldBeInformedThatTheWishlistExceedsTheLimit() {
        assertTrue(exception instanceof WishlistExceedsLimit);
    }
}
