package com.example.flipkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flipkart.exception.ResourceNotFoundException;
import com.example.flipkart.model.Customer;
import com.example.flipkart.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/getCustomerById")
	private ResponseEntity<?> getCustomerById() {
		int customerId = 2;
		try {
			return new ResponseEntity<Customer>(customerService.getCustomer(customerId), HttpStatus.BAD_REQUEST);
		} catch(ResourceNotFoundException e1) {
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping("/saveCustomerUsingRequestParam")
	private Customer saveCustomerUsingRequestParam(
			@RequestParam("a") String name,
			@RequestParam("b") String city,
			@RequestParam("c") String phone,
			@RequestParam("d") String gender
			) {
		Customer customer = Customer.builder().name(name).city(city).phone(phone).gender(gender).build();
		return customerService.saveCustomer(customer);
	}
	
	@RequestMapping("/saveCustomerUsingRequestParam1")
	private Customer saveCustomerUsingRequestParam1(
			@RequestParam String name,
			@RequestParam String city,
			@RequestParam String phone,
			@RequestParam String gender
			) {
		Customer customer = Customer.builder().name(name).city(city).phone(phone).gender(gender).build();
		return customerService.saveCustomer(customer);
	}
	
	@RequestMapping("/saveCustomerUsingPathVariable/{a}/{b}/{c}/{d}")
	private Customer saveCustomerUsingPathVariable(
			@PathVariable("a") String name,
			@PathVariable("b") String city,
			@PathVariable("c") String phone,
			@PathVariable("d") String gender
			) {
		Customer customer = Customer.builder().name(name).city(city).phone(phone).gender(gender).build();
		return customerService.saveCustomer(customer);
	}
	
	@RequestMapping("/saveCustomerUsingPathVariable1/{name}/{city}/{phone}/{gender}")
	private Customer saveCustomerUsingPathVariable1(
			@PathVariable String name,
			@PathVariable String city,
			@PathVariable String phone,
			@PathVariable String gender
			) {
		Customer customer = Customer.builder().name(name).city(city).phone(phone).gender(gender).build();
		return customerService.saveCustomer(customer);
	}

	@RequestMapping("/saveCustomerUsingRequestBody")
	private Customer saveCustomerUsingRequestBody(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}
}
