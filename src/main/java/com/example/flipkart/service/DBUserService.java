package com.example.flipkart.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.flipkart.model.DBUser;
import com.example.flipkart.repository.DBUserRepository;

@Service
public class DBUserService {
	@Autowired
	DBUserRepository dbUserRepository;

	public void saveUser(DBUser user) {

		user.setUserPassword(passwordEncoder().encode(user.getUserPassword()));
		user.setAccountEnabledStatus(0);
		user.setAccountExpiryDate(LocalDate.now().plusMonths(12));
		user.setAccountLockedStatus(0);
		user.setCredentialExpiryDate(LocalDate.now().plusMonths(4));
		dbUserRepository.save(user);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
