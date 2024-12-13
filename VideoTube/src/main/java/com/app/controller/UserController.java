package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.response.LoginResponse;
import com.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User register(@RequestBody User user) throws UserException {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public LoginResponse login(@RequestBody User user) throws UserException {
		return userService.verifyUser(user);
	}
	
	
}
