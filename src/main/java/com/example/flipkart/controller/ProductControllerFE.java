package com.example.flipkart.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.flipkart.dto.ProductDTO;
import com.example.flipkart.dto.RatingDTO;
import com.example.flipkart.service.ProductService;

@Controller
public class ProductControllerFE {

	@Autowired
	ProductService productService;

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping("/allProducts")
	String listOfProducts(Model model) {
		List<ProductDTO> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "list-of-products2";
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping("/addProductForm")
	String addProductsForm(Model model) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setRating(new RatingDTO());
		model.addAttribute("productDTO", productDTO);
		return "add-product-form-new";
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping("/addUpdateProduct")
	public String addOrUpdateProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("imageFile") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {

	        String uploadDir = "uploads/";
	        Files.createDirectories(Paths.get(uploadDir));

	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir, fileName);

	        Files.write(filePath, file.getBytes());

	        // Save only filename or relative path
	        productDTO.setImage("http://localhost:8083/uploads/" + fileName);
	    }

		productService.addProduct(productDTO);
		return "redirect:/allProducts";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/deleteProductFE/{pid}")
	public String deleteProduct(@PathVariable int pid) {
		productService.deleteProduct(pid);
		return "redirect:/allProducts";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/updateProductForm/{pid}")
	public String updateProductForm(@PathVariable int pid, Model model) {
		ProductDTO productDTO = productService.getProduct(pid);
		model.addAttribute("productDTO", productDTO);
		return "add-product-form-new";
	}
	
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}

