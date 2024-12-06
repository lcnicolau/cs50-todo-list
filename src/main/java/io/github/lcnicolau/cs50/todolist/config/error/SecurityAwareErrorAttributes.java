package io.github.lcnicolau.cs50.todolist.config.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static org.springframework.security.web.WebAttributes.ACCESS_DENIED_403;
import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Component
class SecurityAwareErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Throwable getError(WebRequest webRequest) {
        var exception = (Throwable) webRequest.getAttribute(ERROR_EXCEPTION, SCOPE_REQUEST);
        exception = (exception != null) ? exception : getWellKnownSecurityException(webRequest, SCOPE_REQUEST);
        exception = (exception != null) ? exception : getWellKnownSecurityException(webRequest, SCOPE_SESSION);
        return exception;
    }

    protected RuntimeException getWellKnownSecurityException(WebRequest webRequest, int scope) {
        if (webRequest.getAttribute(AUTHENTICATION_EXCEPTION, scope) instanceof AuthenticationException ex) {
            webRequest.removeAttribute(AUTHENTICATION_EXCEPTION, scope);
            return ex;
        }
        if (webRequest.getAttribute(ACCESS_DENIED_403, scope) instanceof AccessDeniedException ex) {
            webRequest.removeAttribute(ACCESS_DENIED_403, scope);
            return ex;
        }
        return null;
    }

}
