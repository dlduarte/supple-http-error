package br.com.dld.supplehttperror.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "timestamp", "status", "message", "path", "details" })
public class ErrorMessage {

	@NotNull
	private Instant timestamp;

	@NotNull
	private HttpStatusCode status;

	@NotBlank
	private String message;

	@NotBlank
	private String path;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String traceId;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ErrorDetail> details;
}
