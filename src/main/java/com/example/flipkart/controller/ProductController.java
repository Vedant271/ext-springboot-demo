package com.example.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flipkart.dto.ProductDTO;
import com.example.flipkart.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/addProducts")
	public ResponseEntity<List<ProductDTO>> addProducts(@RequestBody List<ProductDTO> productDTOList) {
		return new ResponseEntity<List<ProductDTO>>(productService.addProducts(productDTOList), HttpStatus.CREATED);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
		return new ResponseEntity<ProductDTO>(productService.addProduct(productDTO), HttpStatus.CREATED);
	}
}
