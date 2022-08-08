package io.nightfrost.mytubeapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.exceptions.VideoAlreadyExistsException;
import io.nightfrost.mytubeapi.exceptions.VideoNotFoundException;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

	private VideoRepository videoRepository;

	@Override
	public Video getVideo(String name) {
		Video returnVideo = new Video();

		try {
			if (!(returnVideo = videoRepository.findByName(name)).isEmpty()) {
				return returnVideo;
			} else {
				throw new VideoNotFoundException();
			}
		} catch (Exception e) {
			System.out.println(
					"Retrieval of video from database failed, returning empty video object. See stack trace...");
			System.out.println(e.getMessage());
			return returnVideo;
		}
	}

	@Override
	public void saveVideo(MultipartFile file, String name) throws IOException {
		try {
			if (videoRepository.existsByName(name)) {
				throw new VideoAlreadyExistsException();
			} else {
				Video newVideo = new Video(name, file.getBytes());
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
}
