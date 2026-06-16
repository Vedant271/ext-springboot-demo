package com.example.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flipkart.exception.ResourceNotFoundException;
import com.example.flipkart.model.Customer;
import com.example.flipkart.model.Student;
import com.example.flipkart.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer getCustomer(int customerId) throws ResourceNotFoundException {
		if(customerRepository.existsById(customerId)) {
			return customerRepository.findById(customerId).get();
		}
		throw new ResourceNotFoundException("Resource with id " + customerId + " does not exist");
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
