package io.github.dlduarte.supplehttperror;

import lombok.Getter;

import java.util.List;

@Getter
public class RFCValidationException extends RuntimeException {

    private final transient RFCError error;
    private final transient List<FieldValidationError> errors;

    public RFCValidationException(RFCError error, List<FieldValidationError> errors) {
        super(error.defaultMessage());
        this.error = error;
        this.errors = errors;
    }

    public RFCValidationException(RFCError error, String message, List<FieldValidationError> errors) {
        super(message);
        this.error = error;
        this.errors = errors;
    }

    public RFCValidationException(RFCError error, List<FieldValidationError> errors, Throwable throwable) {
        super(error.defaultMessage(), throwable);
        this.error = error;
        this.errors = errors;
    }

    public RFCValidationException(RFCError error, String message, List<FieldValidationError> errors, Throwable throwable) {
        super(message, throwable);
        this.error = error;
        this.errors = errors;
    }
}