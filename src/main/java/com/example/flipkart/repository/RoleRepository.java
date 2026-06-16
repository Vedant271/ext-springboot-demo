package com.example.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flipkart.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>  {

}
