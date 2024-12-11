package io.github.lcnicolau.cs50.todolist.config;

import io.github.lcnicolau.cs50.todolist.users.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
class DataConfig {

    @Bean
    AuditorAware<User> auditorAware(SecurityConfig securityConfig) {
        return securityConfig::getCurrentUser;
    }

}
