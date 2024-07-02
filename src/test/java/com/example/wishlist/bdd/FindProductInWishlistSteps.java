package com.example.wishlist.bdd;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.domain.Wishlist;
import com.example.wishlist.core.exceptions.WishlistNotFound;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.FindProductInWishlist;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class FindProductInWishlistSteps {

    private final WishlistRepository wishlistRepository = Mockito.mock(WishlistRepository.class);
    private final FindProductInWishlist findProductInWishlist = new FindProductInWishlist(wishlistRepository);
    private Boolean result;
    private Exception exception;
    private Wishlist wishlist;

    @Given("a customer with ID '${customerId}' has a wishlist with size $size")
    public void aCustomerWithIDHasAWishlist(String customerId, int size) {
        wishlist = new Wishlist(customerId);
        List<Product> products = ProductDTOMock.createList(size);
        wishlist.setProducts(products);
        Mockito.when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishlist));
    }

    @Given("the wishlist contains a product with ID '${productId}'")
    public void theWishlistContainsAProductWithID(String productId) {
        wishlist = new Wishlist("0", ProductDTOMock.create(productId));
        Mockito.when(wishlistRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.of(wishlist));
    }

    @Given("the wishlist does not contain a product with ID '${productId}'")
    public void theWishlistDoesNotContainAProductWithID(String productId) {
        wishlist = new Wishlist("0", ProductDTOMock.create(productId + "1"));
        Mockito.when(wishlistRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.of(wishlist));
    }

    @Given("a wishlist doesnt exists for customer ID '${customerId}'")
    public void noWishlistExistsForCustomerID(String customerId) {
        Mockito.when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.empty());
    }


    @When("the customer searches for product ID '${productId}'")
    public void theCustomerSearchesForProductID(String productId) {
        try {
            result = findProductInWishlist.findProductById("0", productId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the result should be $size")
    public void theResultShouldBe(String expectedResult) {
        Assertions.assertEquals(Boolean.valueOf(expectedResult), result);
    }

    @Then("a WishlistNotFound exception should be thrown")
    public void aWishlistNotFoundExceptionShouldBeThrown() {
        Assertions.assertTrue(exception instanceof WishlistNotFound);
    }
}

