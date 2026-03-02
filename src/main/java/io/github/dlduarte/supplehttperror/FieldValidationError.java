package io.github.dlduarte.supplehttperror;

public record FieldValidationError(String field, String message, Object rejectedValue) {}