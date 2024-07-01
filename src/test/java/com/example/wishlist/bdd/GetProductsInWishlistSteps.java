package com.example.wishlist.bdd;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.database.WishlistGateway;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import com.example.wishlist.mocks.ProductDTOMock;
import com.example.wishlist.useCases.GetProductsInWishlist;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GetProductsInWishlistSteps {

    private final WishlistGateway wishlistGateway = Mockito.mock(WishlistGateway.class);
    private final GetProductsInWishlist getProductsInWishlist = new GetProductsInWishlist(wishlistGateway);
    private List<Product> result;
    private Wishlist wishlist;

    @Given("a customer with ID '${customerId}' has a wishlist size $size")
    public void aCustomerHasAWishlist(String customerId, int size) {
        wishlist = new Wishlist(customerId);
        List<Product> products = ProductDTOMock.createList(size);
        wishlist.setProducts(products);
        Mockito.when(wishlistGateway.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishlist));
    }

    @Given("the wishlist contains products")
    public void theWishlistContainsProducts() {
        wishlist.getProducts().add(new Product(ProductDTOMock.create("product")));
        Mockito.when(wishlistGateway.findByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.of(wishlist));
    }

    @When("the customer with ID '${customerId}' retrieves all products in the wishlist")
    public void theCustomerRetrievesAllProductsInTheWishlist(String customerId) {
        result = getProductsInWishlist.findAll(customerId);
    }

    @Then("the list of products should be returned")
    public void theListOfProductsShouldBeReturned() {
        Assertions.assertFalse(result.isEmpty());
    }

}
