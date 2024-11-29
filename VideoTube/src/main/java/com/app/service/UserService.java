package com.app.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.response.LoginResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder(12);
	
	public User register(User user) throws UserException {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser.isPresent()) {
			throw new UserException("Email Alredy Taken Please try to Login");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.setFullName(user.getFirstName()+" "+user.getLastName());;
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	public LoginResponse verifyUser(User user) throws UserException {
		Authentication authentication  = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		if(authentication.isAuthenticated()) {
			LoginResponse response = new LoginResponse(user.getEmail(),jwtService.generateToken(user.getEmail()),200);
			return response;
		}
		System.out.println("hlo");
		throw new UserException("Wrong Credintials");
	}
	
}
