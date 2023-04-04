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
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.nightfrost.mytubeapi.dto.UserDTO;
import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	private ModelMapper modelMapper;
	
	// Mock repo and create service.
	UserRepository userRepository = mock(UserRepository.class);
	VideoRepository videoRepository = mock(VideoRepository.class);
	CommentRepository commentRepository = mock(CommentRepository.class);
	PlaylistRepository playlistRepository = mock(PlaylistRepository.class);
	UserService userService = new UserServiceImpl(userRepository, videoRepository, commentRepository,
			playlistRepository, modelMapper);

	// Test values
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
	Set<Comment> commentsAsSet = Set.copyOf(comments);
	User testUserOne = User.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.username(username)
			.password(password)
			.email(email)
			.phone(phone)
			.dob(dob)
			.nationality(nationality)
			.age(age)
			.createdAt(createdAt)
			.enabled(enabled)
			.comments(commentsAsSet)
			.build();
	User testUserTwo = User.builder()
			.id(2)
			.firstName(firstName)
			.lastName(lastName)
			.username(username)
			.password(password)
			.email(email)
			.phone(phone)
			.dob(dob)
			.nationality(nationality)
			.age(age)
			.createdAt(createdAt)
			.enabled(enabled)
			.comments(commentsAsSet)
			.build();
	List<User> listOfUsers = List.of(testUserOne, testUserTwo);
	
	@Test
	public void When_GetAllUsers_Expect_AllUsers() {
		
	}
}