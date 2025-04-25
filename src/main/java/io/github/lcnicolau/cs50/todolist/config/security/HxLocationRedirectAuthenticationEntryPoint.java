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
    private final boolean storeInSession;
    private final RedirectStrategy redirectStrategy;

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl) {
        this(redirectUrl, true);
    }

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl, boolean storeInSession) {
        this(redirectUrl, storeInSession, new HxLocationRedirectStrategy(HttpStatus.UNAUTHORIZED));
    }

    public HxLocationRedirectAuthenticationEntryPoint(String redirectUrl, boolean storeInSession, RedirectStrategy redirectStrategy) {
        this.redirectUrl = redirectUrl;
        this.storeInSession = storeInSession;
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (storeInSession) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authException);
        }
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

}
