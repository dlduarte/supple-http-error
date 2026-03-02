package io.github.dlduarte.supplehttperror;

public interface RFCError {

    String code();

    HttpStatusCode status();

    String title();

    String defaultMessage();
}
