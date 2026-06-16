package com.example.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flipkart.model.DBUser;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
	public DBUser findByUserName(String inputUserName);
}
