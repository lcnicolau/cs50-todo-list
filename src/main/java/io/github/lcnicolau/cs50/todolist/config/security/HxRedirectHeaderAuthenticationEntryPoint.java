package io.github.lcnicolau.cs50.todolist.config.security;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;

import java.io.IOException;

@Slf4j
public class HxRedirectHeaderAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String redirectUrl;

    public HxRedirectHeaderAuthenticationEntryPoint(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authException);
        response.setHeader(HtmxResponseHeader.HX_LOCATION.getValue(), redirectUrl);
        log.debug("Responding with 401 status code");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
