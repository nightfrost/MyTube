package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Comment(s) were not found.")
public class CommentNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806553239569349539L;
}
