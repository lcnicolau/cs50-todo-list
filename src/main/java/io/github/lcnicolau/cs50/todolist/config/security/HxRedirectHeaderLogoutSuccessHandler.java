package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.IOException;

public class HxRedirectHeaderLogoutSuccessHandler implements LogoutSuccessHandler {

    private final LogoutSuccessHandler delegate;

    public HxRedirectHeaderLogoutSuccessHandler(String logoutSuccessUrl) {
        var handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl(logoutSuccessUrl);
        handler.setRedirectStrategy(new HxSuccessRedirectStrategy());
        this.delegate = handler;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        delegate.onLogoutSuccess(request, response, authentication);
    }

}
