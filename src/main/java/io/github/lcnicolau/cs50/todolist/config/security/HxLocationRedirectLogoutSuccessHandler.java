package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.IOException;

public class HxLocationRedirectLogoutSuccessHandler implements LogoutSuccessHandler {

    private final LogoutSuccessHandler delegate;

    public HxLocationRedirectLogoutSuccessHandler(String logoutSuccessUrl) {
        this(logoutSuccessUrl, new HxLocationRedirectStrategy());
    }

    public HxLocationRedirectLogoutSuccessHandler(String logoutSuccessUrl, RedirectStrategy redirectStrategy) {
        var handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl(logoutSuccessUrl);
        handler.setRedirectStrategy(redirectStrategy);
        this.delegate = handler;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        delegate.onLogoutSuccess(request, response, authentication);
    }

}
