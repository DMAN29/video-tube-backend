package com.app.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	private String id;
	
	private String text;
	
	private String author;
	
	private Integer likes;
	
	private Integer dislikes;
	
}
