package com.paymybuddy.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	            .antMatchers("/register", "/webjars/**")
	    			.permitAll()
        		.anyRequest().authenticated();
        
        return http.build();
    }

}