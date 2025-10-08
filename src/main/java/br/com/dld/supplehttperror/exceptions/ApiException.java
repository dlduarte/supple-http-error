package br.com.dld.supplehttperror.exceptions;

import br.com.dld.supplehttperror.payload.ErrorDetail;
import br.com.dld.supplehttperror.payload.ErrorMessage;
import br.com.dld.supplehttperror.payload.HttpStatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class ApiException extends RuntimeException {

	private final HttpStatusCode status;
	private final String message;
	private final List<ErrorDetail> details;

	protected ApiException(HttpStatusCode status, String message) {
		this.status = status;
		this.message = message;
		this.details = new ArrayList<>();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public ErrorMessage response(String requestURI) {
		return response(requestURI, null);
	}

	public ErrorMessage response(String requestURI, String traceId) {
		return ErrorMessage.builder()
		                   .timestamp(Instant.now())
		                   .status(getStatus())
		                   .message(getMessage())
		                   .path(requestURI)
		                   .traceId(traceId)
		                   .details(getDetails())
		                   .build();
	}
}

