package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No playlist(s) found.")
public class PlaylistNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = -781742057298112795L;
}
