package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User(s) were not found.")
public class UserNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 3059916938808249848L;

}
