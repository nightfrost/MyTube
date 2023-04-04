package io.nightfrost.mytubeapi.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.dto.VideoDataDTO;
import io.nightfrost.mytubeapi.exceptions.VideoAlreadyExistsException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

@SpringBootTest
public class VideoServiceTests {
	/*
	 * As we're writing unit tests for the VideoService class, we should be creating
	 * mock classes of other classes it uses. This is to ensure that if we get an
	 * error, we're sure it's the service class itself, not a class that it relies
	 * on.
	 */

	@MockBean
	private VideoRepository videoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VideoServiceImpl videoService = new VideoServiceImpl(videoRepository, modelMapper);
		
	// Test values
	String videoName = "Balenciaga";
	byte[] videoData = new byte[10000000];
	long videoId = 2;
	long userId = 1;
	User user = User.builder()
			.id(userId)
			.build();
	Video returnVideo = Video.builder()
			.id(videoId)
			.name(videoName)
			.user(user)
			.data(videoData)
			.build();
	
	@Test
	public void When_GetVideo_Expect_VideoDTO() throws Exception{
		VideoDTO expected = VideoDTO.builder()
				.id(videoId)
				.name(videoName)
				.userId(userId)
				.build();
				
		//When findById is called, return expected
		when(videoRepository.getReferenceById(videoId))
			.thenReturn(returnVideo);
		
		VideoDTO actual = videoService.getVideo(videoId);
		assertEquals(expected, actual);
		
		//Verify that videoRepository was called.
		verify(videoRepository, times(1)).getReferenceById(videoId);
	}
	
	@Test
	public void When_GetVideoWrongVideoId_Expect_isNullTrue() throws Exception{
		Video emptyVideoObject = null;
		
		when(videoRepository.getReferenceById(3L))
			.thenReturn(emptyVideoObject);
		
		VideoDTO actual = videoService.getVideo(3L);
		assertNull(actual);
	}
	
	@Test
	public void When_GetVideoData_expect_DataExists() throws Exception {
		SecureRandom.getInstanceStrong().nextBytes(videoData);
		VideoDataDTO expected = VideoDataDTO.builder()
				.data(videoData)
				.build();
		
		when(videoRepository.getReferenceById(videoId))
			.thenReturn(returnVideo);
		
		VideoDataDTO actual = videoService.getVideoData(videoId);
		
		assertEquals(expected, actual);
		assertTrue(actual.getData().length > 0);
		verify(videoRepository, times(1)).getReferenceById(videoId);
	}
	
	@Test
	public void When_SaveVideo_expect_VideoSavedMessage() throws Exception {
		//fill byte array with random data and convert to multipartfile
		SecureRandom.getInstanceStrong().nextBytes(videoData);
		TestMultipartFile testmpFile = new TestMultipartFile(videoData);
		
		String expected = "Video saved";
		
		//when saveVideo is called, return video saved.
		when(videoRepository.saveAndFlush(returnVideo))
			.thenReturn(returnVideo);
		String actual = videoService.saveVideo(testmpFile, videoName, user);
		
		//assert response
		assertEquals(expected, actual);
	}
	
	@Test
	public void When_SaveVideoNameAlreadyExists_Expect_VideoAlreadyExistsException() throws Exception {
//		SecureRandom.getInstanceStrong().nextBytes(videoData);
//		TestMultipartFile testmpFile = new TestMultipartFile(videoData);
//		
//		when(videoRepository.saveAndFlush(returnVideo))
//			.thenThrow(new VideoAlreadyExistsException());
//		when(videoRepository.existsByName(videoName))
//			.thenThrow(new VideoAlreadyExistsException());
//		Exception exception = assertThrows(VideoAlreadyExistsException.class, () -> {
//			String actual = videoService.saveVideo(testmpFile, videoName, user);
//			String actualClone = videoService.saveVideo(testmpFile, videoName, user);
//	    });
	}
}
