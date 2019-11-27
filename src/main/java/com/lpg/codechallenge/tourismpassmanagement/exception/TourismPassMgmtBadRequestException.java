package com.lpg.codechallenge.tourismpassmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TourismPassMgmtBadRequestException extends RuntimeException {

	public TourismPassMgmtBadRequestException(final String message) {
		super(message);
	}

}
