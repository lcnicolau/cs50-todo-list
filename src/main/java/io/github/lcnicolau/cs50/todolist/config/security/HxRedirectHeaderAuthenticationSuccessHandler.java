package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class HxRedirectHeaderAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationSuccessHandler delegate;

    public HxRedirectHeaderAuthenticationSuccessHandler(String defaultSuccessUrl) {
        this(defaultSuccessUrl, false);
    }

    public HxRedirectHeaderAuthenticationSuccessHandler(String defaultSuccessUrl, boolean alwaysUse) {
        var handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl(defaultSuccessUrl);
        handler.setAlwaysUseDefaultTargetUrl(alwaysUse);
        handler.setRedirectStrategy(new HxLocationRedirectStrategy());
        this.delegate = handler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        delegate.onAuthenticationSuccess(request, response, authentication);
    }

}
