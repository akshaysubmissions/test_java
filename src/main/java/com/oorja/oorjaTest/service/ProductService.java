package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.model.Products;

import java.util.Optional;

public interface ProductService {

   public boolean findById(int id);
    Products findByIdForQuantity(int id);

    public Products addProduct(Products product);
}
