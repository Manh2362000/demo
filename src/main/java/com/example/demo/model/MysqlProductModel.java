package com.example.demo.model;

import com.example.demo.entity.Product;

import java.util.List;

public class MysqlProductModel implements ProductModel {
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Product update(String id, Product product) {
        return null;
    }
}
