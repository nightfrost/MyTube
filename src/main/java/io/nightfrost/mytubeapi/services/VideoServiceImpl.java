package io.nightfrost.mytubeapi.services;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.dto.VideoDataDTO;
import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.exceptions.VideoAlreadyExistsException;
import io.nightfrost.mytubeapi.exceptions.VideoNotFoundException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

	private VideoRepository videoRepository;
	
	private ModelMapper modelMapper;

	@Deprecated
	@Override
	public VideoDTO getVideo(String name) {
		Video returnVideo = new Video();
		VideoDTO returnVideoDto = new VideoDTO();

		try {
			if (!(returnVideo = videoRepository.findByName(name)).isEmpty()) {
				returnVideoDto = modelMapper.map(returnVideo, VideoDTO.class);
				return returnVideoDto;
			} else {
				throw new VideoNotFoundException();
			}
		} catch (Exception e) {
			System.out.println(
					"Retrieval of video from database failed, returning empty video object. See stack trace...");
			System.out.println(e.getMessage());
			return returnVideoDto;
		}
	}

	@Override
	public void saveVideo(MultipartFile file, String name, User userId) throws IOException {
		try {
			if (videoRepository.existsByName(name)) {
				throw new VideoAlreadyExistsException();
			} else {
				Video newVideo = new Video(name, file.getBytes(), userId);
				videoRepository.saveAndFlush(newVideo);
			}
		} catch (Exception e) {
			System.out.println("Video was not saved. Check stack trace...");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<String> getAllVideoNames() {
		return videoRepository.getAllEntryNames();
	}
	
	@Override
	public VideoDataDTO getVideoData(long videoId) {
		Video returnVideo = new Video();
		VideoDataDTO returnVideoDataDTO = new VideoDataDTO();
		
		try {
			if ((returnVideo = videoRepository.getReferenceById(videoId)) != null) {
				returnVideoDataDTO = modelMapper.map(returnVideo, VideoDataDTO.class);
				
				return returnVideoDataDTO;
			} else {
				throw new VideoNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of Video(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			
			return returnVideoDataDTO;
		}
	}

	@Override
	public VideoDTO getVideo(long videoId) {
		VideoDTO returnVideoDTO = null;
		Video returnVideo = null;
		
		try {
			if ((returnVideo = videoRepository.getReferenceById(videoId)) != null) {
				returnVideoDTO = modelMapper.map(returnVideo, VideoDTO.class);
				
				return returnVideoDTO;
			} else {
				throw new VideoNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of Video(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnVideoDTO;
		}
	}
}
