package com.example.wishlist.core.DTO;

import com.example.wishlist.core.domain.Wishlist;
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
