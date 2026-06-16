//package com.example.flipkart.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurity 
//{
//	 //Authentication
//	 @Bean
//	    public UserDetailsService userDetailsService() {
//
//	        UserDetails admin1 = User.builder()
//	                .username("jetha")
//	                .password(passwordEncoder().encode("jetha123"))
//	                .authorities("ADMIN")
//	                .build();
//
//	        UserDetails user1 = User.builder()
//	                .username("bagha")
//	                .password(passwordEncoder().encode("bagha123"))
//	                .authorities("USER")
//	                .build();
//
//	        return new InMemoryUserDetailsManager(admin1, user1);
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	    
//	    //Authorisation
//	    @Bean  
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        
//	        http.authorizeRequests()
//	        .requestMatchers("/allProducts","/addProductForm").hasAnyAuthority("USER","ADMIN")
//	        .requestMatchers("/deleteProductFE/**","/updateProductForm/**").hasAuthority("ADMIN")
//	        .anyRequest().authenticated()
//	        .and()
//	        .formLogin().loginProcessingUrl("/login").successForwardUrl("/allProducts").permitAll()
//	        .and()
//	        .logout().logoutSuccessUrl("/login").permitAll()
//	        .and()
//	        .exceptionHandling().accessDeniedPage("/403")
//	        .and()
//	        .cors().and().csrf().disable();
//	        return http.build();
//	    }
//}
//