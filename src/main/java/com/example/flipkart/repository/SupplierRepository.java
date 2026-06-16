package com.example.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flipkart.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
