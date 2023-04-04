package io.nightfrost.mytubeapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.dto.VideoDataDTO;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;

public interface VideoService {
	VideoDTO getVideo(String name);
	
	VideoDTO getVideo(long videoId);
	
	VideoDataDTO getVideoData(long videoId);
	
	String saveVideo(MultipartFile file, String name, User userid) throws IOException;
	
	List<String> getAllVideoNames();
}
