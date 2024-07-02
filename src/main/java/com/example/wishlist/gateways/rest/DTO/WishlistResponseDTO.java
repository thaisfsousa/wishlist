package com.example.wishlist.gateways.rest.DTO;

import com.example.wishlist.domain.Wishlist;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class WishlistResponseDTO {

    private String customerId;
    private List<ProductResponseDTO> products;

    public WishlistResponseDTO(Wishlist wishlist) {
        this.customerId = wishlist.getCustomerId();
        this.products = wishlist.getProducts().stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }
}
