package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Comment already exists.")
public class CommentAlreadyExistsException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = -7551638257218783665L;

}
