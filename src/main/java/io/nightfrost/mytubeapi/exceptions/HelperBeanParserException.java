package io.nightfrost.mytubeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Parser error, check input")
public class HelperBeanParserException extends RuntimeException {

}
