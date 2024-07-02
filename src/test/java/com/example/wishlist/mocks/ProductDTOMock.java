package com.example.wishlist.mocks;

import com.example.wishlist.core.domain.Product;
import com.example.wishlist.core.DTO.ProductResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductDTOMock {

    public static ProductResponseDTO create(String productId){
        return new ProductResponseDTO(productId, "Product " + productId, 10.0);
    }

    public static ProductResponseDTO create(){
        return create("1");
    }

    public static List<ProductResponseDTO> createProductList(Integer size){
        return IntStream.range(0,size).mapToObj(String::valueOf).map(ProductDTOMock::create).collect(Collectors.toList());
    }

    public static List<Product> createList(Integer size){
        return createProductList(size).stream().map(Product::new).collect(Collectors.toList());
    }
}
