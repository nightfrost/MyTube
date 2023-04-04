package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No playlist(s) found.")
public class PlaylistNotFoundException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = -781742057298112795L;


}

