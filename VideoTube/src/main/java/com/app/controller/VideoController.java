package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.Dto.VideoDto;
import com.app.exception.UserException;
import com.app.model.User;
import com.app.response.UploadVideoResponse;
import com.app.service.JwtService;
import com.app.service.UserService;
import com.app.service.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
	
	private final VideoService videoService;
	private final UserService userService;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file,@RequestHeader("Authorization") String jwt) throws UserException {
		return videoService.uploadVideo(file,jwt);
	}
	
	@PostMapping("/thumbnail")
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadThumbnail(@RequestParam("file")MultipartFile file, @RequestParam("videoId") String videoId){
		return videoService.uploadThumbnail(file,videoId);
	}
	@PutMapping()
	@ResponseStatus(HttpStatus.OK)
	public VideoDto editVideoMetaData(@RequestBody VideoDto videoDto) {
		return videoService.editVideo(videoDto);
	}
	
	@GetMapping("/{videoId}")
	@ResponseStatus(HttpStatus.OK)
	public VideoDto getVideoDetails(@PathVariable String videoId,@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserByJwt(jwt);
		return videoService.getVideoDetails(videoId);
	}
}
