package io.nightfrost.mytubeapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.models.Video;

public interface VideoService {
	Video getVideo(String name);
	
	void saveVideo(MultipartFile file, String name) throws IOException;
	
	List<String> getAllVideoNames();
}
