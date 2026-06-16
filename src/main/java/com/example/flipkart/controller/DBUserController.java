package com.example.flipkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.flipkart.model.DBUser;
import com.example.flipkart.model.Role;
import com.example.flipkart.repository.RoleRepository;
import com.example.flipkart.service.DBUserService;

@Controller
public class DBUserController {
	@Autowired
	DBUserService dbUserService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping("/saveUserForm")
	public String saveUserForm(Model model)
	{
		DBUser user=new DBUser();
		
		List<Role> roles=roleRepository.findAll();
		
		model.addAttribute("user",user);
		model.addAttribute("roles",roles);
		return "save-user-form";
	}
	
	@PostMapping("/saveUserFE")
	public String saveUserFE(@ModelAttribute DBUser user)
	{
		dbUserService.saveUser(user);
		return "redirect:/allProducts";
	}

}
