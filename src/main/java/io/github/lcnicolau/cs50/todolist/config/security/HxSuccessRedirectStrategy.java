package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;

import java.io.IOException;

import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequestHeader.HX_REQUEST;
import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader.HX_LOCATION;

class HxSuccessRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (request.getHeader(HX_REQUEST.getValue()) == null) {
            super.sendRedirect(request, response, url);
        } else {
            var redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
            redirectUrl = response.encodeRedirectURL(redirectUrl);
            this.logger.debug(LogMessage.format("Redirecting to %s", redirectUrl));
            response.setHeader(HX_LOCATION.getValue(), redirectUrl);
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().flush();
        }
    }

}
