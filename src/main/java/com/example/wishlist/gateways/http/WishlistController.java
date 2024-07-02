package com.example.wishlist.gateways.http;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.http.DTO.ProductResponseDTO;
import com.example.wishlist.gateways.http.DTO.WishlistResponseDTO;
import com.example.wishlist.useCases.AddProductToWishlist;
import com.example.wishlist.useCases.FindProductInWishlist;
import com.example.wishlist.useCases.GetProductsInWishlist;
import com.example.wishlist.useCases.RemoveProductFromWishlist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/wishlist")
public class WishlistController {

    private final AddProductToWishlist addProductToWishlist;
    private final FindProductInWishlist findProductInWishlist;
    private final GetProductsInWishlist getProductsInWishlist;
    private final RemoveProductFromWishlist removeProductFromWishlist;

    @Autowired
    public WishlistController(AddProductToWishlist addProductToWishlist,
                              FindProductInWishlist findProductInWishlist,
                              GetProductsInWishlist getProductsInWishlist,
                              RemoveProductFromWishlist removeProductFromWishlist) {
        this.addProductToWishlist = addProductToWishlist;
        this.findProductInWishlist = findProductInWishlist;
        this.getProductsInWishlist = getProductsInWishlist;
        this.removeProductFromWishlist = removeProductFromWishlist;
    }

    @Operation(description = "" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns all Products from a customer."),
    })
    @GetMapping(path = "customers/{customerId}/products")
    public List<ProductResponseDTO> getProducts(@PathVariable String customerId){
        List<Product> response = getProductsInWishlist.findAll(customerId);
        List<ProductResponseDTO> products = new ArrayList<>();
        for (Product product : response){
            products.add(new ProductResponseDTO(product));
        }
        return products;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns if a specific product already exists in customer's wishlist."),
    })
    @GetMapping(path = "customers/{customerId}/products/{productId}")
    public Boolean getProductById (@PathVariable String customerId, @PathVariable String productId){
        return findProductInWishlist.findProductById(customerId, productId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Returns a new wishlist for customer, adding a new product."),
            @ApiResponse(responseCode = "400",
                    description = "The wishlist is already full."),
    })
    @PostMapping(path = "customers/{customerId}/products")
    public WishlistResponseDTO saveProduct(@PathVariable(name = "customerId") String customerId,
                                           @Valid @RequestBody final ProductResponseDTO product) {
        Wishlist response = addProductToWishlist.saveProduct(customerId, product);
        return new WishlistResponseDTO(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The product was successfully deleted."),
            @ApiResponse(responseCode = "404",
                    description = "The product was not found."),
            @ApiResponse(responseCode = "404",
                    description = "The wishlist was not found."),
    })
    @DeleteMapping(path = "customers/{customerId}/products/{productId}")
    public String removeProduct(@PathVariable String customerId,
                                                          @PathVariable String productId){
        removeProductFromWishlist.deleteProduct(customerId, productId);
        return("Product deleted");
    }
}
