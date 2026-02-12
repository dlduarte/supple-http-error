package br.com.dld.supplehttperror.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access=lombok.AccessLevel.PRIVATE)
public class ExceptionUtils {
    
    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return (cause == null || cause == throwable) ? throwable : getRootCause(cause);
    }
    
    public static String getRootCauseMessage(Throwable throwable) {
        return getRootCause(throwable).getMessage();
    }
}
