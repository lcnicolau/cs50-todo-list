package io.github.lcnicolau.cs50.todolist.planner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PlannerInitializer {

    static final Planner USER = new Planner("User", "user@todolist.com", "");
    static final Planner ADMIN = new Planner("Admin", "admin@todolist.com", "");

    private final PlannerRepository plannerRepository;

    PlannerInitializer(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }


    @PostConstruct
    void init() {
        plannerRepository.saveAll(List.of(USER, ADMIN));
    }

}
