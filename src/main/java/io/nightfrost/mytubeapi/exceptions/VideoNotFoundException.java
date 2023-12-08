package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "A video with that id was not found")
public class VideoNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -4940303275013723739L;

}
