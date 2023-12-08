package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.exceptions.CommentNotFoundException;
import io.nightfrost.mytubeapi.models.Comment;

import java.util.List;


public interface CommentService {
	List<Comment> getAllCommentsByVideoId(long id);

	Comment getCommentById(long id) throws CommentNotFoundException;

	Comment addComment(Comment newComment);

	Comment updateComment(long id, Comment newComment) throws CommentNotFoundException;

	String deleteComment(long id);
}
