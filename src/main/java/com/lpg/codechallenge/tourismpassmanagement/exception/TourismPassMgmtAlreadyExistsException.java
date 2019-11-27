package com.lpg.codechallenge.tourismpassmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TourismPassMgmtAlreadyExistsException extends Exception {

	public TourismPassMgmtAlreadyExistsException() {

	}

	public TourismPassMgmtAlreadyExistsException(final String message) {
		super(message);
	}

}
