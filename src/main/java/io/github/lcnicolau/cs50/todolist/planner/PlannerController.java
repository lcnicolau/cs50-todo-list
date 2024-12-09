package io.github.lcnicolau.cs50.todolist.planner;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class PlannerController {

    private final PlannerService plannerService;

    PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }


    @PostMapping("/signup")
    ModelAndView signup(@Valid Planner planner, HtmxResponse response) {
        var saved = plannerService.signup(planner);
        response.setPushUrl("/login");
        response.setRetarget("main");
        return new ModelAndView("pages/login :: content", "email", saved.getEmail());
    }

}
