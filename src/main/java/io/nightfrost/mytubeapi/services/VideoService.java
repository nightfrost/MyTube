package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.dto.VideoDataDTO;
import io.nightfrost.mytubeapi.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
	VideoDTO getVideo(String name);
	
	VideoDTO getVideo(long videoId);
	
	VideoDataDTO getVideoData(long videoId);
	
	String saveVideo(MultipartFile file, String name, User userid);
	
	List<String> getAllVideoNames();
}
