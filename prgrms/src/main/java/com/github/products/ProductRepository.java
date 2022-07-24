package com.github.products;

import com.github.products.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(long id);

    List<Product> findAll();

    void updateProductReviewCount(long id);

}