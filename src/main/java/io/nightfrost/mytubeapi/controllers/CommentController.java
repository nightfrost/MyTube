package io.nightfrost.mytubeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.services.CommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "comment")
@AllArgsConstructor
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/video/{id}")
	public ResponseEntity<List<Comment>> getAllCommentsByVideoId(@PathVariable long id) {
		return ResponseEntity.ok(commentService.getAllCommentsByVideoId(id));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable long id) {
		return ResponseEntity.ok(commentService.getCommentById(id));
	}

	@PostMapping()
	public ResponseEntity<Comment> addComment(@RequestBody Comment newComment) {
		return ResponseEntity.ok(commentService.addComment(newComment));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment updatedComment, @PathVariable long id) {
		return ResponseEntity.ok(commentService.updateComment(id, updatedComment));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteComment(@PathVariable long id) {
		return ResponseEntity.ok(commentService.deleteComment(id));
	}
}
