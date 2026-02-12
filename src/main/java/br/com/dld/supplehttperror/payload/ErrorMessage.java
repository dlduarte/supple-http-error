package br.com.dld.supplehttperror.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Instant timestamp;
        private HttpStatusCode status;
        private String message;
        private String path;
        private String traceId;
        private List<ErrorDetail> details;

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(HttpStatusCode status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder details(List<ErrorDetail> details) {
            this.details = details;
            return this;
        }

        public Builder details(ErrorDetail... details) {
            this.details = Arrays.asList(details);
            return this;
        }

        public ErrorMessage build() {
            return new ErrorMessage(timestamp, status, message, path, traceId, details);
        }
    }
}
