package io.nightfrost.mytubeapi.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

public class UserServiceImplTest {
		//Mock repo and create service.
		UserRepository userRepository = mock(UserRepository.class);
		VideoRepository videoRepository = mock(VideoRepository.class);
		CommentRepository commentRepository = mock(CommentRepository.class);
		PlaylistRepository playlistRepository = mock(PlaylistRepository.class);
		UserService userService = new UserServiceImpl(userRepository, videoRepository, commentRepository, playlistRepository);
		
		//Test values
		long id = 1;
		String firstName = "John";
		String lastName = "Doe";
		String username = "TheRealJohnDoe";
		String password = "password";
		String email = "JohnDoe@JohnDoe.dk";
		String phone = "+45 11223344";
		Date dob = Date.valueOf(LocalDate.now());
		String nationality = "Denmark";
		int age = 42;
		Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
		boolean enabled = true;
		Comment newComment1 = new Comment();
		Comment newComment2 = new Comment();
		List<Comment> comments = List.of(newComment1, newComment2);
		
		@Test
		void getUser() {
			User expected = new User(id, firstName, lastName, username, password, email,
					phone, dob, nationality, age, createdAt, enabled);
			User actual = userService.getUserById(id);
			
			//When getReferenceById is called, return expected
			when(userRepository.getReferenceById(id))
				.thenReturn(expected);
			
			assertEquals(expected, actual);
			
			//Verify the call has been made
			verify(userRepository, times(1)).getReferenceById(id);
		}
}