package com.example.flipkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flipkart.model.Supplier;
import com.example.flipkart.service.SupplierService;

@RestController
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping("/saveSupplierUsingRequestParam")
	public Supplier saveSupplierUsingRequestParam(
			@RequestParam("a") String name,
			@RequestParam("b") String city,
			@RequestParam("c") String phone
			) {
		Supplier supplier = Supplier.builder().name(name).city(city).phone(phone).build();
		return supplierService.saveSupplier(supplier);
	}
	
	@RequestMapping("/saveSupplierUsingRequestParam1")
	public Supplier saveSupplierUsingRequestParam1(
			@RequestParam String name,
			@RequestParam String city,
			@RequestParam String phone
			) {
		Supplier supplier = Supplier.builder().name(name).city(city).phone(phone).build();
		return supplierService.saveSupplier(supplier);
	}
	
	@RequestMapping("/saveSupplierUsingPathVariable/{a}/{b}/{c}")
	public Supplier saveSupplierUsingPathVariable(
			@PathVariable("a") String name,
			@PathVariable("b") String city,
			@PathVariable("c") String phone
			) {
		Supplier supplier = Supplier.builder().name(name).city(city).phone(phone).build();
		return supplierService.saveSupplier(supplier);
	}
	
	@RequestMapping("/saveSupplierUsingPathVariable1/{name}/{city}/{phone}")
	public Supplier saveSupplierUsingPathVariable1(
			@PathVariable String name,
			@PathVariable String city,
			@PathVariable String phone
			) {
		Supplier supplier = Supplier.builder().name(name).city(city).phone(phone).build();
		return supplierService.saveSupplier(supplier);
	}
	
	@RequestMapping("/saveSupplierUsingRequestBody")
	public Supplier saveSupplierUsingRequestBody(@RequestBody Supplier supplier) {
		return supplierService.saveSupplier(supplier);
	}
	
	@RequestMapping("/getSupplierById")
	public Supplier getSupplierById() {
		int supplierId = 2;
		return supplierService.getSupplier(supplierId);
	}
}
