package com.example.wishlist.useCases.validator;

import com.example.wishlist.domain.Product;

import java.util.List;

public class MaxProductsValidator {

    public static boolean exceedsProducts(List<Product> product, long maxProducts){
        return product.size() == maxProducts;
    }
}
