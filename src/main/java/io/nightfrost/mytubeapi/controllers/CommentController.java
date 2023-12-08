package io.nightfrost.mytubeapi.controllers;

import io.nightfrost.mytubeapi.exceptions.CommentNotFoundException;
import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "comment")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping(value = "/video/{id}")
	public ResponseEntity<List<Comment>> getAllCommentsByVideoId(@PathVariable long id) {
		return ResponseEntity.ok(commentService.getAllCommentsByVideoId(id));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable long id) throws CommentNotFoundException {
		return ResponseEntity.ok(commentService.getCommentById(id));
	}

	@PostMapping()
	public ResponseEntity<Comment> addComment(@RequestBody Comment newComment) {
		return ResponseEntity.ok(commentService.addComment(newComment));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment updatedComment, @PathVariable long id) throws CommentNotFoundException {
		return ResponseEntity.ok(commentService.updateComment(id, updatedComment));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteComment(@PathVariable long id) {
		return ResponseEntity.ok(commentService.deleteComment(id));
	}
}
