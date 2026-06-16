package com.example.flipkart.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.flipkart.dto.ProductDTO;
import com.example.flipkart.exception.ResourceNotFoundException;
import com.example.flipkart.model.Product;
import com.example.flipkart.model.Student;
import com.example.flipkart.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<ProductDTO> addProducts(List<ProductDTO> productDTOList){
		List<Product> productList = productDTOList.stream().map(dto -> modelMapper.map(dto, Product.class)).toList();
		List<Product> savedProducts = productRepository.saveAll(productList);
		List<ProductDTO> savedProductDTOs = savedProducts.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
		return savedProductDTOs;
	}
	
	public ProductDTO addProduct(ProductDTO productDTO){
		Product product = modelMapper.map(productDTO, Product.class);
		Product savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductDTO.class);
	}
	
	public ProductDTO getProduct(int productId) throws ResourceNotFoundException {
		if(productRepository.existsById(productId)) {
			Product product = productRepository.findById(productId).get();
			ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
			return productDTO;
		}
		throw new ResourceNotFoundException("Resource with id " + productId + " does not exist");
	}
	
	public List<ProductDTO> getAllProducts(){
		List<Product> allProducts = productRepository.findAll();
		List<ProductDTO> allProductsDTOs = allProducts.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
		return allProductsDTOs;
	}

	public void deleteProduct(int inputId){
		if(productRepository.existsById(inputId)) {
			productRepository.deleteById(inputId);
			return;
		}
		throw new ResourceNotFoundException("Resource not available in database");
	}
}
