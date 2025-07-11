package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;

import java.io.IOException;

/**
 * Handles navigation on {@link HttpStatus#UNAUTHORIZED} access by delegating to the {@link HxLocationRedirectStrategy},
 * providing an htmx-friendly redirect mechanism.
 *
 * @author LC Nicolau
 */
public class HxLocationRedirectAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String redirectUrl;
    private final boolean allowSessionCreation;
    private final RedirectStrategy redirectStrategy;

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl) {
        this(redirectUrl, true);
    }

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl, boolean allowSessionCreation) {
        this(redirectUrl, allowSessionCreation, new HxLocationRedirectStrategy(HttpStatus.UNAUTHORIZED));
    }

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl, boolean allowSessionCreation, RedirectStrategy redirectStrategy) {
        this.redirectUrl = redirectUrl;
        this.allowSessionCreation = allowSessionCreation;
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (request.getSession(false) != null || this.allowSessionCreation) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authException);
        }
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

}
