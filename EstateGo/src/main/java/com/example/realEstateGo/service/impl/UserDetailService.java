package com.example.realEstateGo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.realEstateGo.entity.Users;
import com.example.realEstateGo.repository.UserRepository;

@Component
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = userRepository.findByName(username);
		return user.map(UserPrincipal::new).orElseThrow(() -> new
				 UsernameNotFoundException("user not found"));
//		if (user == null)
//			throw new UsernameNotFoundException("User not found " + username);
//		return new UserPrincipal(user);

	}

}
