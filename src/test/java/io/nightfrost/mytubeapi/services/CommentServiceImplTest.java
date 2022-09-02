package io.nightfrost.mytubeapi.services;

import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;

public class CommentServiceImplTest {
	
	//mock repository and use it in service.
	CommentRepository commentRepository = mock(CommentRepository.class);
	VideoRepository videoRepository = mock(VideoRepository.class);
	UserRepository userRepository = mock(UserRepository.class);
	CommentService commentService = new CommentServiceImpl(commentRepository, userRepository, videoRepository);
	
	//Test values for comment
	private long comment_id = 2;
	private String body = "Test comment";
	private int likes = 2;
	private int dislikes = 1;
	private Video video = new Video();
	private User user = new User();
	private boolean isPinned = false;
	private OffsetDateTime createdAtComment = OffsetDateTime.now();
	private OffsetDateTime updatedAt = OffsetDateTime.now();
	
	//Test values for user
	long user_id = 1;
	String firstName = "John";
	String lastName = "Doe";
	String username = "TheRealJohnDoe";
	String password = "password";
	String email = "JohnDoe@JohnDoe.dk";
	String phone = "+45 11223344";
	Date dob = Date.valueOf(LocalDate.now());
	String nationality = "Denmark";
	int age = 42;
	Timestamp createdAtUser = Timestamp.valueOf(LocalDateTime.now());
	boolean enabled = true;
	Comment newComment1 = new Comment();
	Comment newComment2 = new Comment();
	List<Comment> comments = List.of(newComment1, newComment2);
	
	//Test values for video
	String videoTestName = "myVid";
	
	@Test
	public void getCommentsByVideoId() {
		List<Comment> expected = List.of(new Comment(comment_id, body, likes, dislikes,video, user, isPinned, createdAtComment, updatedAt),
				new Comment(comment_id, body, likes, dislikes,video, user, isPinned, createdAtComment, updatedAt));
		List<Comment> actual = commentService.getAllCommentsByVideoId(comment_id);
		
		
	}
	
	@Test
	public void getCommentById() {
		
	}
	
	@Test
	public void addComment() {
		
	}
	
	@Test
	public void updateComment() {
		
	}
	
	@Test
	public void deleteComment() {
		
	}
}
