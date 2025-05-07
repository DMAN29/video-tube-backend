package com.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String password;
	private Set<String> subscribedToUsers = new HashSet<>();
	private Set<String> subsribers = new HashSet<>();
	private List<String> videoHistory = new ArrayList<>();
	private Set<String> likedVideos = new HashSet<>();
	private Set<String> dislikedVideos = new HashSet<>();

}
