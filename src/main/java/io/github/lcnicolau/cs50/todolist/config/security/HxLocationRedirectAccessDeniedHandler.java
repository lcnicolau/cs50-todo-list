package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * Handles navigation on {@link HttpStatus#FORBIDDEN} access by delegating to the {@link HxLocationRedirectStrategy},
 * providing an htmx-friendly redirect mechanism.
 *
 * @author LC Nicolau
 */
public class HxLocationRedirectAccessDeniedHandler implements AccessDeniedHandler {

    private final String redirectUrl;
    private final boolean storeInSession;
    private final RedirectStrategy redirectStrategy;

    public HxLocationRedirectAccessDeniedHandler(String redirectUrl) {
        this(redirectUrl, true);
    }

    public HxLocationRedirectAccessDeniedHandler(String redirectUrl, boolean storeInSession) {
        this(redirectUrl, storeInSession, new HxLocationRedirectStrategy(HttpStatus.FORBIDDEN));
    }

    public HxLocationRedirectAccessDeniedHandler(String redirectUrl, boolean storeInSession, RedirectStrategy redirectStrategy) {
        this.redirectUrl = redirectUrl;
        this.storeInSession = storeInSession;
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (storeInSession) {
            request.getSession().setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        }
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

}
