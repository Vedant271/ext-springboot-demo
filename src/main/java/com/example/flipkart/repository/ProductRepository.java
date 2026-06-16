package com.example.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flipkart.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
