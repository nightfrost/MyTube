package io.nightfrost.mytubeapi.services;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VideoServiceImplTest {
	/*
	 * As we're writing unit tests for the VideoService class, we should be creating
	 * mock classes of other classes it uses. This is to ensure that if we get an
	 * error, we're sure it's the service class itself, not a class that it relies
	 * on.
	 */
	VideoRepository videoRepository = mock(VideoRepository.class);
	VideoService videoService = new VideoServiceImpl(videoRepository);

	// Test values
	String testName = "myVid";

	@Test
	void getVideo() {
		Video expected = new Video(testName, null);
		Video actual = videoService.getVideo(testName);

		//When findByName is called, return expected
		when(videoRepository.findByName(testName))
			.thenReturn(expected);
		//When existsByName is called, return true
		when(videoRepository.existsByName(testName))
			.thenReturn(true);
		
		assertEquals(expected, actual);
		
		//Verify that videoRepository was called.
		verify(videoRepository, times(1)).existsByName(testName);
		verify(videoRepository, times(1)).findByName(testName);
	}

	@Test
	void getAllVideoNames() {
		List<String> expected = List.of("myVid", "otherVid");
		List<String> actual = videoService.getAllVideoNames();
		
		//When getAllentryNames is called, return expected list.
		when(videoRepository.getAllEntryNames())
			.thenReturn(expected);
		
		assertEquals(expected, actual);
		
		//Verify that videoRepository was called.
		verify(videoRepository, times(1)).getAllEntryNames();

	}

	@Test
	void saveVideo() throws IOException {
		MultipartFile file = mock(MultipartFile.class);
		Video testVid = new Video(testName, file.getBytes());
		
		videoService.saveVideo(file, testName);
		
		//Verify that videoRepository was called.
		verify(videoRepository, times(1)).existsByName(testName);
		verify(videoRepository, times(1)).saveAndFlush(testVid);
	}
}
