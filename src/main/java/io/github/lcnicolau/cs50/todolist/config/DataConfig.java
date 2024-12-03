package io.github.lcnicolau.cs50.todolist.config;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
class DataConfig {

    @Bean
    AuditorAware<Planner> auditorAware() {
        // TODO: Get the current user
        return () -> Optional.of(Planner.USER);
    }

}
