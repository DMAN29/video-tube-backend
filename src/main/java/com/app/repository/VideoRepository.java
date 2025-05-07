package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.model.Video;

public interface VideoRepository extends MongoRepository<Video, String>{

}
