package io.github.dlduarte.supplehttperror;

import lombok.Getter;

@Getter
public class RFCException extends RuntimeException {

    private final transient RFCError error;

    public RFCException(RFCError error) {
        super(error.defaultMessage());
        this.error = error;
    }

    public RFCException(RFCError error, String message) {
        super(message);
        this.error = error;
    }

    public RFCException(RFCError error, Throwable throwable) {
        super(error.defaultMessage(), throwable);
        this.error = error;
    }

    public RFCException(RFCError error, String message, Throwable throwable) {
        super(message, throwable);
        this.error = error;
    }
}