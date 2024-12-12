package io.github.lcnicolau.cs50.todolist.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class HxLocationRedirectAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationSuccessHandler delegate;

    public HxLocationRedirectAuthenticationSuccessHandler(String defaultSuccessUrl) {
        this(defaultSuccessUrl, false);
    }

    public HxLocationRedirectAuthenticationSuccessHandler(String defaultSuccessUrl, boolean alwaysUse) {
        this(defaultSuccessUrl, alwaysUse, new HxLocationRedirectStrategy());
    }

    public HxLocationRedirectAuthenticationSuccessHandler(String defaultSuccessUrl, boolean alwaysUse, RedirectStrategy redirectStrategy) {
        var handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl(defaultSuccessUrl);
        handler.setAlwaysUseDefaultTargetUrl(alwaysUse);
        handler.setRedirectStrategy(redirectStrategy);
        this.delegate = handler;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        delegate.onAuthenticationSuccess(request, response, authentication);
    }

}
