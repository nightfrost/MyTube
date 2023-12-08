package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.exceptions.CommentNotFoundException;
import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.exceptions.VideoNotFoundException;
import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

	CommentRepository commentRepository;

	UserRepository userRepository;

	final
	VideoRepository videoRepository;

	public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, VideoRepository videoRepository) {
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.videoRepository = videoRepository;
	}

	@Override
	public List<Comment> getAllCommentsByVideoId(long id) {
		List<Comment> returnCommentList = new ArrayList<>();
		try {
			if (!(returnCommentList = commentRepository.getCommentsByVideoId(id)).isEmpty()) {
				return returnCommentList;
			} else {
				throw new CommentNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of comment(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnCommentList;
		}
	}

	@Override
	public Comment getCommentById(long id) throws CommentNotFoundException {
		Comment returnComment = new Comment();
		try {
			return returnComment = commentRepository.getReferenceById(id);
		} catch (EntityNotFoundException e) {
			throw new CommentNotFoundException();
		} catch (Exception e) {
			System.out.println("Retrieval of comment(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnComment;
		}
	}

	@Override
	public Comment addComment(Comment newComment) {
		Comment returnComment = new Comment();
		try {
			if (!videoRepository.existsById(newComment.getVideo().getId())) {
				throw new VideoNotFoundException();
			} else if (!userRepository.existsById(newComment.getUser().getId())) {
				throw new UserNotFoundException();
			} else {
				commentRepository.saveAndFlush(newComment);
				return returnComment;
			}
		} catch (Exception e) {
			System.out.println("Retrieval of comment(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnComment;
		}
	}

	@Override
	public Comment updateComment(long id, Comment newComment) throws CommentNotFoundException {
		Comment returnComment = null;

		try {
			returnComment = commentRepository.getReferenceById(id);
			HelperService.partialUpdate(returnComment, newComment);
			commentRepository.save(returnComment);

			return returnComment;
		} catch (EntityNotFoundException e) {
			throw new CommentNotFoundException();
		} catch (BeansException e) {
			System.out.print("Failed to copy values into comment object, save failed... Returning empty object\nPrinting message...");
			System.out.println(e.getMessage());

			return returnComment;
		} catch (Exception e) {
			System.out.println("Saving user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());

			return returnComment;
		}
	}

	@Override
	public String deleteComment(long id) {
		try {
			if (commentRepository.existsById(id)) {
				commentRepository.deleteById(id);
				return commentRepository.existsById(id) ? "Comment not deleted." : "Comment with ID: " + id + " deleted.";
			} else {
				throw new CommentNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Deleting user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return null;
		}
	}

}