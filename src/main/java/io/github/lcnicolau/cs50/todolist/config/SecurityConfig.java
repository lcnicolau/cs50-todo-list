package io.github.lcnicolau.cs50.todolist.config;

import io.github.lcnicolau.cs50.todolist.config.security.*;
import io.github.lcnicolau.cs50.todolist.planner.Planner;
import io.github.lcnicolau.cs50.todolist.planner.PlannerUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

import java.util.Optional;

import static org.springframework.boot.autoconfigure.security.StaticResourceLocation.JAVA_SCRIPT;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources;

@Configuration
class SecurityConfig {

    @Bean
    @Profile("dev")
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(toH2Console()); // TODO: only dev
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(login -> login
                        .usernameParameter("email")
                        .loginPage("/login")
                        .failureForwardUrl("/error?login")
                        .successHandler(new HxRedirectHeaderAuthenticationSuccessHandler("/home?login"))
                ).logout(logout -> logout
                        .logoutSuccessHandler(new HxRedirectHeaderLogoutSuccessHandler("/home?logout"))
                ).exceptionHandling(handler -> handler
                        .defaultAuthenticationEntryPointFor(
                                new HxRedirectHeaderAuthenticationEntryPoint("/login?unauthorized"),
                                new RequestHeaderRequestMatcher("HX-Request"))
                        .defaultAccessDeniedHandlerFor(
                                new HxRedirectHeaderAccessDeniedHandler("/error?forbidden"),
                                new RequestHeaderRequestMatcher("HX-Request"))
                ).authorizeHttpRequests(config -> config
                        .requestMatchers(
                                "/",
                                "/home",
                                "/signup",
                                "/login",
                                "/error"
                        ).permitAll()
                        .requestMatchers(
                                toStaticResources().atCommonLocations().excluding(JAVA_SCRIPT)
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    Optional<Planner> getCurrentUser() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(PlannerUserDetails.class::isInstance)
                .map(PlannerUserDetails.class::cast)
                .map(PlannerUserDetails::getPlanner);
    }

}
