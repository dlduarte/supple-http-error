package br.com.dld.supplehttperror.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetail {
    private String field;
    private String error;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String field;
        private String error;

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(field, error);
        }
    }
}