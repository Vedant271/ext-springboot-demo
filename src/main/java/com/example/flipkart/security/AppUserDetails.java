package com.example.flipkart.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.flipkart.model.DBUser;
import com.example.flipkart.model.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppUserDetails implements UserDetails {
	DBUser dbUser;

	public AppUserDetails(DBUser dbUser) {
		this.dbUser = dbUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> userRoles = dbUser.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		for (Role role : userRoles) {
			System.out.println("ROLE FROM DB = " + role.getRoleName());
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.dbUser.getUserPassword();
	}

	@Override
	public String getUsername() {
		return this.dbUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		log.info("Expiry Date = {}", dbUser.getAccountExpiryDate());
		log.info("Today = {}", LocalDate.now());
		log.info("Is Account Non Expired " + dbUser.getAccountExpiryDate().isAfter(LocalDate.now()));
		return dbUser.getAccountExpiryDate().isAfter(LocalDate.now());
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		log.info("Is Credentials Non Expired " + dbUser.getCredentialExpiryDate().isAfter(LocalDate.now()));
		return dbUser.getCredentialExpiryDate().isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		log.info("Is Account Non Locked " + (dbUser.getAccountLockedStatus()==0));
		return dbUser.getAccountLockedStatus()==0;
	}

	@Override
	public boolean isEnabled() {
		log.info("Is Enabled "+ (dbUser.getAccountEnabledStatus()==0));
		return dbUser.getAccountEnabledStatus()==0;
	}
}
