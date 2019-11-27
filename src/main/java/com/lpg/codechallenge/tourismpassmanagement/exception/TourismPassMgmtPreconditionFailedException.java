package com.lpg.codechallenge.tourismpassmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class TourismPassMgmtPreconditionFailedException extends Exception {

	public TourismPassMgmtPreconditionFailedException() {

	}

	public TourismPassMgmtPreconditionFailedException(final String message) {
		super(message);
	}

}
