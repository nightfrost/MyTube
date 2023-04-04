package io.nightfrost.mytubeapi.services;

import java.util.List;

import io.nightfrost.mytubeapi.models.Comment;


public interface CommentService {
	List<Comment> getAllCommentsByVideoId(long id);

	Comment getCommentById(long id);

	Comment addComment(Comment newComment);

	Comment updateComment(long id, Comment newComment);

	String deleteComment(long id);
}
