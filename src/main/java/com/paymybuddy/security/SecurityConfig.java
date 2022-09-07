package com.paymybuddy.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import com.paymybuddy.service.UserService;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
	            .antMatchers("/register", "/webjars/**").permitAll()
	    		.anyRequest().authenticated()
        		.and()
        		.formLogin()
        			.loginPage("/login")
        				.permitAll();
      
        return http.build();
    }
    

    @Autowired
    private EncoderConfig encode;
    
    @Autowired
	private UserService userService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encode.passwordEncoder());
        return auth;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
   
    
    
    // TODO trouver un meilleur emplacement pour la méthode
    public String userInfos() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userInfoEmail = authentication.getName();
    	
    	return userInfoEmail;
    }

}