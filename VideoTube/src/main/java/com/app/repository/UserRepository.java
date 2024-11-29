package com.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	public Optional<User> findByEmail(String email);
	
}
