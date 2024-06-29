package com.example.wishlist.unit.mocks;

import com.example.wishlist.gateways.http.DTO.ProductDTO;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProductDTOMock {

    public static ProductDTO create(String productId){
        return new ProductDTO(productId, "Test " + productId, 10.0);
    }

    public static ProductDTO create(){
        return create("1");
    }

    public static List<ProductDTO> createProductList(Integer size){
        return IntStream.range(0,size).mapToObj(String::valueOf).map(ProductDTOMock::create).toList();
    }
}
