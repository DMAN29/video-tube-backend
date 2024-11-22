package com.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.Dto.VideoDto;
import com.app.model.Video;
import com.app.repository.VideoRepository;
import com.app.response.UploadVideoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {

	private final S3Service s3Service;
	
	private final VideoRepository videoRepository;
	
	public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
		String videoUrl = s3Service.uploadFile(multipartFile);
		
		var video = new Video();
		video.setVideoUrl(videoUrl);
		
		var savedVideo = videoRepository.save(video);
		
		return new UploadVideoResponse(savedVideo.getVideoUrl(),savedVideo.getId());
	}
	public Video findById(String id) {
		return videoRepository.findById(id)
			.orElseThrow(()-> new IllegalArgumentException("Cannot find video by id - "+id));
		
	}
	
	
	public VideoDto editVideo(VideoDto videoDto) {
		var savedVideo = findById(videoDto.getId());
		
		savedVideo.setTitle(videoDto.getTitle());
		savedVideo.setDescription(videoDto.getDescription());
		savedVideo.setTags(videoDto.getTags());
		savedVideo.setVideoStatus(videoDto.getVideoStatus());
		savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
		
		videoRepository.save(savedVideo);
		return videoDto;
	}
	
	
	public String uploadThumbnail(MultipartFile file, String videoId) {
		var savedVideo = findById(videoId);
		String thumbnailUrl = s3Service.uploadFile(file);
		savedVideo.setThumbnailUrl(thumbnailUrl);
		videoRepository.save(savedVideo);
		return thumbnailUrl;
	}
	
	public VideoDto getVideoDetails(String videoId) {
		var savedVideo = findById(videoId);
		VideoDto videoDto = new VideoDto();
		videoDto.setVideoUrl(savedVideo.getVideoUrl());
		videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
		videoDto.setId(savedVideo.getId());
		videoDto.setTitle(savedVideo.getTitle());
		videoDto.setTags(savedVideo.getTags());
		videoDto.setDescription(savedVideo.getDescription());
		videoDto.setVideoStatus(savedVideo.getVideoStatus());
		return videoDto;
	}
}
