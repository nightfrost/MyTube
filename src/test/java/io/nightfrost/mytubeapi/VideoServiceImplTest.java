package io.nightfrost.mytubeapi;

import org.junit.jupiter.api.Test;

import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import io.nightfrost.mytubeapi.services.VideoService;
import io.nightfrost.mytubeapi.services.VideoServiceImpl;

import static org.mockito.Mockito.*;
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

		when(videoRepository.findByName(testName)).thenReturn(expected);
		when(videoRepository.existsByName(testName)).thenReturn(true);
		
		assertEquals(expected, actual);
		verify(videoRepository, times(1)).existsByName(testName);
		verify(videoRepository, times(1)).findByName(testName);
	}

	@Test
	void getAllVideoNames() {

	}

	@Test
	void saveVideo() {

	}
}
