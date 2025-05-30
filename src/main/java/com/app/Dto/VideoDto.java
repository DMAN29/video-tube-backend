package com.app.Dto;

import java.util.Set;

import com.app.model.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
	
	private String id;
	
	private String title;
	
	private String description;
	
	private Set<String> tags;
	
	private String videoUrl;
	
	private VideoStatus videoStatus;
	
	private String thumbnailUrl;
	
	private String userEmail;
	
}
