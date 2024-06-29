package com.example.wishlist.gateways.http;

import com.example.wishlist.domain.Product;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateways.http.DTO.ProductDTO;
import com.example.wishlist.useCases.AddProductToWishlist;
import com.example.wishlist.useCases.FindProductInWishlist;
import com.example.wishlist.useCases.GetProductsInWishlist;
import com.example.wishlist.useCases.RemoveProductFromWishlist;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

    @GetMapping(path = "customers/{customerId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProducts(@PathVariable String customerId){
        List<Product> response = getProductsInWishlist.findAll(customerId);
        List<ProductDTO> products = new ArrayList<>();
        for (Product product : response){
            products.add(new ProductDTO(product));
        }
        return products;
    }

    @GetMapping(path = "customers/{customerId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean getProductById (@PathVariable String customerId, @PathVariable String productId){
        return findProductInWishlist.findProductById(customerId, productId);
    }

    @PostMapping(path = "customers/{customerId}/products/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Wishlist saveProduct(@PathVariable(name = "customerId") String customerId,
                                                          @Valid @RequestBody final ProductDTO product) {
        return addProductToWishlist.saveProduct(customerId, product);
    }

    @DeleteMapping(path = "customers/{customerId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String removeProduct(@PathVariable String customerId,
                                                          @PathVariable String productId){
        removeProductFromWishlist.deleteProduct(customerId, productId);
        return("Product deleted");
    }
}
