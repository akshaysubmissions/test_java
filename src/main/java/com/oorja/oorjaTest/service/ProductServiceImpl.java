package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.model.Products;
import com.oorja.oorjaTest.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;
    @Override
    public boolean findById(int id) {

       Optional <Products> products = productDao.findById(id);
        return products.isPresent();
    }

    @Override
    public Products findByIdForQuantity(int id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Products addProduct(Products product) {
        return productDao.save(product);


    }
}
