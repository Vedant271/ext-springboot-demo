package com.example.flipkart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityNew 
{	    
    //Authorisation
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	http.authenticationProvider(getAuthenticationProvider());
//        http.authorizeRequests()
//        .requestMatchers("/allProducts","/addProductForm", "/saveUserForm").hasAnyAuthority("USER","ADMIN")
//        .requestMatchers("/deleteProductFE/**","/updateProductForm/**").hasAuthority("ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().loginProcessingUrl("/login").successForwardUrl("/allProducts").permitAll()
//        .and()
//        .logout().logoutSuccessUrl("/login").permitAll()
//        .and()
//        .exceptionHandling().accessDeniedPage("/403")
//        .and()
//        .cors().and().csrf().disable();
//        return http.build();
//    }
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.authenticationProvider(getAuthenticationProvider());
        http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/allProducts").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .cors().and().csrf().disable();
        return http.build();
    }

    @Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
    @Bean
    public UserDetailsService getUserDetailsService() {
		return new AppUserDetailsService();
	}
	
    @Bean
    public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

