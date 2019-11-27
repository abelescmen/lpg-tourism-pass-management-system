package com.lpg.codechallenge.tourismpassmanagement.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value = "com.lpg.codechallenge.tourismpassmanagement")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TourismPassMgmtExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String ERROR_MESSAGE_FORMAT = "Request: [{}] | raised [{}]";

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleError(final HttpServletRequest req, final Exception exception) {
		final ResponseStatus rs = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
		if (rs != null) {
			return createResponseBody(req, exception, rs.value());
		}

		final String uri = req.getRequestURI();

		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		if (exception instanceof HttpClientErrorException) {
			httpStatus = ((HttpClientErrorException) exception).getStatusCode();

			if (null != uri && HttpStatus.NOT_FOUND.equals(httpStatus)) {
				log.debug(ERROR_MESSAGE_FORMAT, uri, exception);
			}
		} else {
			log.error(ERROR_MESSAGE_FORMAT, uri, exception, ExceptionUtils.getRootCause(exception));
		}
		return createResponseBody(req, exception, httpStatus);
	}

	@ExceptionHandler(UndeclaredThrowableException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleUndeclaredThrowableException(final HttpServletRequest req, final UndeclaredThrowableException exception) throws Exception {
		final Throwable ex = exception.getUndeclaredThrowable().getCause();
		if (ex instanceof HttpClientErrorException) {
			return handleError(req, (HttpClientErrorException) ex);
		} else {
			final String uri = req.getRequestURI();
			final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			log.error(ERROR_MESSAGE_FORMAT, uri, exception, ex);

			return createResponseBody(req, exception, httpStatus);
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(final HttpServletRequest req, final IllegalArgumentException exception) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		final String message = exception.getMessage();
		final String timestamp = getCurrentTimestamp();
		final String uri = req.getRequestURI();

		log.error("[{}] | class [{}] | Request: [{}] | message [{}]", timestamp, ClassUtils.getShortName(exception.getClass()), uri, message);
		return createResponseBody(req, exception, httpStatus);
	}

	private ResponseEntity<Map<String, Object>> createResponseBody(final HttpServletRequest req, final Exception exception, HttpStatus httpStatus) {
		final Map<String, Object> body = getResponseMap(req, httpStatus.value());
		if (null != ExceptionUtils.getRootCause(exception)) {
			body.put("exception", ExceptionUtils.getRootCause(exception).getMessage());
		} else {
			body.put("exception", exception.getMessage());
		}
		return new ResponseEntity<>(body, null, httpStatus);
	}

	private Map<String, Object> getResponseMap(final HttpServletRequest req, final int status) {
		final Map<String, Object> body = new HashMap<>();
		body.put("url", req.getRequestURL().toString());
		body.put("timestamp", LocalDateTime.now().toString());
		body.put("status", status);
		return body;
	}

	private String getCurrentTimestamp() {
		return LocalDateTime.now().toString();
	}

}
