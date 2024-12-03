package io.github.lcnicolau.cs50.todolist.planner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import static io.github.lcnicolau.cs50.todolist.planner.Planner.ADMIN;
import static io.github.lcnicolau.cs50.todolist.planner.Planner.USER;

@Component
class PlannerInitializer {

    private final PlannerRepository plannerRepository;

    PlannerInitializer(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }


    @PostConstruct
    void init() {
        USER.setId(plannerRepository.save(USER).getId());
        USER.setPassword(null);
        ADMIN.setId(plannerRepository.save(ADMIN).getId());
        ADMIN.setPassword(null);
    }

}
