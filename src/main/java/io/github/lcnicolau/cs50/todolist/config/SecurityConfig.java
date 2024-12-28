package io.github.lcnicolau.cs50.todolist.config;

import io.github.lcnicolau.cs50.todolist.config.security.HxLocationRedirectAccessDeniedHandler;
import io.github.lcnicolau.cs50.todolist.config.security.HxLocationRedirectAuthenticationEntryPoint;
import io.github.lcnicolau.cs50.todolist.config.security.HxLocationRedirectAuthenticationSuccessHandler;
import io.github.lcnicolau.cs50.todolist.config.security.HxLocationRedirectLogoutSuccessHandler;
import io.github.lcnicolau.cs50.todolist.users.User;
import io.github.lcnicolau.cs50.todolist.users.UserDetails;
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

import java.util.Optional;

import static org.springframework.boot.autoconfigure.security.StaticResourceLocation.JAVA_SCRIPT;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(login -> login
                        .usernameParameter("email")
                        .loginPage("/login")
                        .failureForwardUrl("/error?login")
                        .successHandler(new HxLocationRedirectAuthenticationSuccessHandler("/tasks?login", true))
                ).logout(logout -> logout
                        .logoutSuccessHandler(new HxLocationRedirectLogoutSuccessHandler("/home?logout"))
                ).exceptionHandling(handler -> handler
                        .authenticationEntryPoint(new HxLocationRedirectAuthenticationEntryPoint("/login?unauthorized"))
                        .accessDeniedHandler(new HxLocationRedirectAccessDeniedHandler("/error?forbidden"))
                ).authorizeHttpRequests(config -> config
                        .requestMatchers(
                                toStaticResources().atCommonLocations().excluding(JAVA_SCRIPT)
                        ).permitAll()
                        .requestMatchers(
                                "/",
                                "/home",
                                "/about",
                                "/signup",
                                "/login",
                                "/error"
                        ).permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    @Profile("dev")
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(toH2Console());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    Optional<User> getCurrentUser() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .map(UserDetails::getUser);
    }

}
