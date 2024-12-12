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

public class HxLocationRedirectAccessDeniedHandler implements AccessDeniedHandler {

    private final String redirectUrl;
    private final RedirectStrategy redirectStrategy;

    public HxLocationRedirectAccessDeniedHandler(String redirectUrl) {
        this(redirectUrl, new HxLocationRedirectStrategy(HttpStatus.FORBIDDEN));
    }

    public HxLocationRedirectAccessDeniedHandler(String redirectUrl, RedirectStrategy redirectStrategy) {
        this.redirectUrl = redirectUrl;
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        request.getSession().setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

}
