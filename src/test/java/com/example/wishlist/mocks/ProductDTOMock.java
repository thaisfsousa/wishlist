package com.example.wishlist.mocks;

import com.example.wishlist.domain.Product;
import com.example.wishlist.gateways.http.DTO.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductDTOMock {

    public static ProductDTO create(String productId){
        return new ProductDTO(productId, "Product " + productId, 10.0);
    }

    public static ProductDTO create(){
        return create("1");
    }

    public static List<ProductDTO> createProductList(Integer size){
        return IntStream.range(0,size).mapToObj(String::valueOf).map(ProductDTOMock::create).collect(Collectors.toList());
    }

    public static List<Product> createList(Integer size){
        return createProductList(size).stream().map(Product::new).collect(Collectors.toList());
    }
}
