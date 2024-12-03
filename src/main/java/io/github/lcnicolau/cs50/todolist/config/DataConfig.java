package io.github.lcnicolau.cs50.todolist.config;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
class DataConfig {

    private final SecurityConfig securityConfig;

    DataConfig(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }


    @Bean
    AuditorAware<Planner> auditorAware() {
        return securityConfig::getCurrentUser;
    }

}
