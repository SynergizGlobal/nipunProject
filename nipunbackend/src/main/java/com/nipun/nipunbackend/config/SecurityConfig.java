package com.nipun.nipunbackend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
	
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable()) // 🔥 disable CSRF for APIs
	            .cors(Customizer.withDefaults()) // 🔥 enable CORS
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 🔥 allow preflight
	                .requestMatchers("/api/contractor/**").permitAll() // 🔥 allow your APIs
	                .anyRequest().authenticated()
	            );

	        return http.build();
	    }

}
