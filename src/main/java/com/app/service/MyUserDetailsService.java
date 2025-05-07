package com.app.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.model.UserPrincipal;
import com.app.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User>  user = userRepository.findByEmail(email);
		
		if(user.isEmpty()) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User not Found with email"+ email);
		}
		
		
		return new UserPrincipal(user.get());
	}

}
