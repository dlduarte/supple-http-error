package io.github.dlduarte.supplehttperror;

import java.net.URI;
import java.time.Instant;
import java.util.List;

public record RFCProblem(
        URI type,
        String title,
        int status,
        String detail,
        URI instance,
        String code,
        String traceId,
        Instant timestamp,
        List<FieldValidationError> errors
) {}
