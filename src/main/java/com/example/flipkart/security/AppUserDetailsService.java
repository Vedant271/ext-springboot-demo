package com.example.flipkart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.flipkart.model.DBUser;
import com.example.flipkart.repository.DBUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	DBUserRepository dbUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DBUser dbUser = dbUserRepository.findByUserName(username);
		
		if(dbUser == null) throw new UsernameNotFoundException("User does not exist");
		
		return new AppUserDetails(dbUser);
	}
}
