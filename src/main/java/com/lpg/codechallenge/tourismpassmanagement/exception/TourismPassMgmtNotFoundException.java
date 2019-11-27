package com.lpg.codechallenge.tourismpassmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TourismPassMgmtNotFoundException extends Exception {

	public TourismPassMgmtNotFoundException() {

	}

	public TourismPassMgmtNotFoundException(final String message) {
		super(message);
	}

}
