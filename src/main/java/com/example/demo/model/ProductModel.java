package com.example.demo.model;

import com.example.demo.entity.Product;


import java.util.List;

public interface ProductModel {
    List<Product> findAll();

    Product save(Product product);

    Product findById(String id);

    boolean delete(String id);

    Product update(String id, Product product);
}
