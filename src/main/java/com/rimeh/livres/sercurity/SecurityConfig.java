package com.rimeh.livres.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
	    http
	        // CSRF désactivé (API REST)
	        .csrf(csrf -> csrf.disable())

	        // CORS configuration
	        .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
	            @Override
	            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                CorsConfiguration cors = new CorsConfiguration();

	                cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                cors.setAllowedMethods(Collections.singletonList("*"));
	                cors.setAllowedHeaders(Collections.singletonList("*"));
	                cors.setExposedHeaders(Collections.singletonList("Authorization"));

	                return cors;
	            }
	        }))

	        // Session stateless (JWT)
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )

	        // Autorisations
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/api/livre/all/**").hasAnyAuthority("ADMIN","USER")
	            .requestMatchers(HttpMethod.GET,"/api/livre/getbyid/**").hasAnyAuthority("ADMIN","USER")
	            .requestMatchers(HttpMethod.POST,"/api/addlivre/**").hasAuthority("ADMIN")
	            .requestMatchers(HttpMethod.PUT,"/api/updatelivre/**").hasAuthority("ADMIN")
	            .requestMatchers(HttpMethod.DELETE,"/api/dellivre/**").hasAuthority("ADMIN")
	            .anyRequest().authenticated()
	        )

	        // JWT filter
	        .addFilterBefore(
	            new JWTAuthorizationFilter(),
	            BasicAuthenticationFilter.class
	        );

	    return http.build();
	}
}