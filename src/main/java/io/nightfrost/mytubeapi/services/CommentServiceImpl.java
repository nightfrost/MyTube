package io.nightfrost.mytubeapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nightfrost.mytubeapi.exceptions.CommentNotFoundException;
import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.exceptions.VideoNotFoundException;
import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
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
	public Comment getCommentById(long id) {
		Comment returnComment = new Comment();
		try {
			if ((returnComment = commentRepository.getReferenceById(id)) != null) {
				return returnComment;
			} else {
				throw new CommentNotFoundException();
			}
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
	public Comment updateComment(long id, Comment newComment) {
		Comment returnComment = null;
		try {
			if ((returnComment = commentRepository.getReferenceById(id)) == null) {
				throw new CommentNotFoundException();
			} else {
				returnComment = (Comment) HelperService.partialUpdate(returnComment, newComment);
				commentRepository.save(returnComment);
				return returnComment;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into comment object... Returning empty object\nPrinting message...");
			System.out.println(e.getMessage());
			return returnComment = null;
		} catch (Exception e) {
			System.out.println("Saving user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnComment = null;
		}
	}

	@Override
	public String deleteComment(long id) {
		// TODO: Implement delete cascade for user and video...
		return null;
	}

}
