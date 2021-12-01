package com.taller3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {       
        http
        	.authorizeRequests()
        	.antMatchers("/").authenticated()
        	.antMatchers("/secure/**").authenticated()
        	.antMatchers("/users/**").permitAll()
        	.antMatchers("/admin/**").hasRole("ADMIN")
        	.antMatchers("/operator/**").hasRole("OPERATOR")
        	.anyRequest().authenticated()
        	.and().httpBasic()
        	.and().formLogin()
            	.loginProcessingUrl("/login")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
