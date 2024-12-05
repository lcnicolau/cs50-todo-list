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

public class HxRedirectHeaderAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String redirectUrl;
    private final RedirectStrategy redirectStrategy;

    public HxRedirectHeaderAuthenticationEntryPoint(String redirectUrl) {
        this(redirectUrl, new HxLocationRedirectStrategy(HttpStatus.UNAUTHORIZED));
    }

    public HxRedirectHeaderAuthenticationEntryPoint(String redirectUrl, RedirectStrategy redirectStrategy) {
        this.redirectUrl = redirectUrl;
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authException);
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

}
