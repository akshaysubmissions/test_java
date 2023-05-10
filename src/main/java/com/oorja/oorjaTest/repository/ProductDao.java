package com.oorja.oorjaTest.repository;

import com.oorja.oorjaTest.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {


}
