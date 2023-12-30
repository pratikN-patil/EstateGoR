package com.example.realEstateGo.appSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.realEstateGo.service.impl.UserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailService();
	}

	@Bean

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/estateGo/welcome", "/addadmin","/agent/agents","/agent/{agentId}","/customer/**")
				.permitAll().and().authorizeHttpRequests()

				.requestMatchers(HttpMethod.POST, "/agent/add").hasAnyAuthority("Admin")
				.requestMatchers(HttpMethod.POST, "/propertyAdd").hasAnyAuthority("Agent")
				.requestMatchers(HttpMethod.GET, "/propertyList").hasAnyAuthority("Customer").
				requestMatchers(HttpMethod.POST, "/appointment/addp").hasAnyAuthority("Customer").
				requestMatchers(HttpMethod.GET, "/appointment/{appointmentId}").hasAnyAuthority("Agent").
				requestMatchers(HttpMethod.POST, "/reviews").hasAnyAuthority("Customer").
				requestMatchers(HttpMethod.POST, "/reviews/{reviewId}").hasAnyAuthority("Customer").
				requestMatchers(HttpMethod.GET, "/reviews/view").hasAnyAuthority("Customer").
				requestMatchers(HttpMethod.GET, "/listAppointments").hasAnyAuthority("Agent").
				requestMatchers(HttpMethod.GET, "/property/name/{propertyName}").hasAnyAuthority("Agent").
				requestMatchers(HttpMethod.GET, "/property/{propertyAddress}").hasAnyAuthority("Customer").
				
				anyRequest()
				.authenticated().and().formLogin().permitAll().and().httpBasic();

		return http.build();
	}
}