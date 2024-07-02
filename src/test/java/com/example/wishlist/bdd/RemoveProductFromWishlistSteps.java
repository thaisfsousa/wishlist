package com.example.wishlist.bdd;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.exceptions.ProductNotFound;
import com.example.wishlist.exceptions.WishlistNotFound;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.RemoveProductFromWishlist;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;

import java.util.Optional;

public class RemoveProductFromWishlistSteps {
    private final WishlistGateway wishlistGateway = Mockito.mock(WishlistGateway.class);
    private Wishlist wishlist;
    private Exception exception;

    @Given("a customer with ID '$customerId' has a wishlist containing product '$productId'")
    public void aCustomerHasAWishlistWithProduct(String customerId, String productId) {
        wishlist = new Wishlist(customerId);
        wishlist.getProducts().add(new Product(ProductDTOMock.create(productId)));

        Mockito.when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
    }

    @Given("a customer with ID '$customerId' has a wishlist not containing product '$productId'")
    public void aCustomerHasAWishlistWithoutProduct(String customerId, String productId) {
        wishlist = new Wishlist(customerId);

        Mockito.when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));
    }

    @Given("no wishlist exists for customer ID '$customerId'")
    public void noWishlistForCustomerID(String customerId) {
        Mockito.when(wishlistGateway.findByCustomerId(customerId)).thenReturn(Optional.empty());
    }

    @When("customer '$customerId' tries to remove the product with ID '$productId' from the wishlist")
    public void theCustomerTriesToRemoveTheProductFromTheWishlist(String customerId, String productId) {
        try {
            new RemoveProductFromWishlist(wishlistGateway).deleteProduct(customerId, productId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the product '$productId' should be removed from the wishlist")
    public void theProductShouldBeRemovedFromTheWishlist() {
        Assertions.assertTrue(wishlist.getProducts().isEmpty());
        Mockito.verify(wishlistGateway).save(wishlist);
    }

    @Then("a ProductNotFound exception should be thrown")
    public void aProductNotFoundExceptionShouldBeThrown() {
        Assertions.assertInstanceOf(ProductNotFound.class, exception);
    }

    @Then("a exception NotFound should be thrown")
    public void aWishlistNotFoundExceptionShouldBeThrown() {
        Assertions.assertInstanceOf(WishlistNotFound.class, exception);
    }
}
