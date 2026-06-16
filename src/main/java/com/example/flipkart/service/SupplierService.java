package com.example.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flipkart.model.Customer;
import com.example.flipkart.model.Supplier;
import com.example.flipkart.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public Supplier getSupplier(int supplierId) {
		return supplierRepository.findById(supplierId).get();
	}
}
