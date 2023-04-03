package io.nightfrost.mytubeapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

@SpringBootTest
public class VideoControllerTests {
	/*
	 * As we're writing unit tests for the VideoService class, we should be creating
	 * mock classes of other classes it uses. This is to ensure that if we get an
	 * error, we're sure it's the service class itself, not a class that it relies
	 * on.
	 */

	@MockBean
	private VideoService videoService;
	
	@MockBean
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// Test values
	String testName = "Balenciaga";
	long videoId = 2;
	long userId = 1;

	@Test
	public void whenGetVideo_returnVideo() throws Exception{
		VideoDTO expected = VideoDTO.builder()
				.id(videoId)
				.name(testName)
				.userId(userId)
				.build();
				
		//When findById is called, return expected
		when(videoService.getVideo(2L))
			.thenReturn(expected);
		
		VideoDTO actual = videoService.getVideo(videoId);
		assertEquals(expected, actual);
		
		//Verify that videoRepository was called.
		verify(videoService, times(1)).getVideo(videoId);
	}
	
//	@Test
//	void getVideoData() {
//		User userData = new User();
//		userData.setId(1);
//		Video expected = new Video(testName, null, userData);
//		VideoDTO actual = videoService.getVideo(testName);
//
//		//When findByName is called, return expected
//		when(videoRepository.findByName(testName))
//			.thenReturn(expected);
//		//When existsByName is called, return true
//		when(videoRepository.existsByName(testName))
//			.thenReturn(true);
//		
//		assertEquals(expected, actual);
//		
//		//Verify that videoRepository was called.
//		verify(videoRepository, times(1)).existsByName(testName);
//		verify(videoRepository, times(1)).findByName(testName);
//	}
//
//	@Test
//	void getAllVideoNames() {
//		List<String> expected = List.of("myVid", "otherVid");
//		List<String> actual = videoService.getAllVideoNames();
//		
//		//When getAllentryNames is called, return expected list.
//		when(videoRepository.getAllEntryNames())
//			.thenReturn(expected);
//		
//		assertEquals(expected, actual);
//		
//		//Verify that videoRepository was called.
//		verify(videoRepository, times(1)).getAllEntryNames();
//
//	}
//
//	@Test
//	void saveVideo() throws IOException {
//		User userData = new User();
//		userData.setId(1);
//		MultipartFile file = mock(MultipartFile.class);
//		Video testVid = new Video(testName, file.getBytes(), userData);
//		
//		videoService.saveVideo(file, testName, userData);
//		
//		//Verify that videoRepository was called.
//		verify(videoRepository, times(1)).existsByName(testName);
//		verify(videoRepository, times(1)).saveAndFlush(testVid);
//	}
}
