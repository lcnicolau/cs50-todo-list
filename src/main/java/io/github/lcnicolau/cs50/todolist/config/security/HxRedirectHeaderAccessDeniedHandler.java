package io.github.lcnicolau.cs50.todolist.config.security;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
public class HxRedirectHeaderAccessDeniedHandler implements AccessDeniedHandler {

    private final String redirectUrl;

    public HxRedirectHeaderAccessDeniedHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        request.getSession().setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        response.setHeader(HtmxResponseHeader.HX_LOCATION.getValue(), redirectUrl);
        log.debug("Responding with 403 status code");
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }

}
