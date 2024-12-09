package io.github.lcnicolau.cs50.todolist;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import io.github.lcnicolau.cs50.todolist.planner.PlannerUserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTriggerAfterSettle;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
class MainController {

    private final PlannerUserService plannerUserService;


    @GetMapping({"/", "/home"})
    @HxTriggerAfterSettle("{\"tab-change\": \"home\"}")
    String home(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/home :: content" : "pages/home";
    }

    @GetMapping("/tasks")
    @HxTriggerAfterSettle("{\"tab-change\": \"tasks\"}")
    String tasks(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/tasks :: content" : "pages/tasks";
    }

    @GetMapping("/users")
    @HxTriggerAfterSettle("{\"tab-change\": \"users\"}")
    String users(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/users :: content" : "pages/users";
    }

    @GetMapping("/signup")
    @HxTriggerAfterSettle("{\"tab-change\": \"signup\"}")
    String signup(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/signup :: content" : "pages/signup";
    }

    @PostMapping("/signup")
    void signup(@Valid Planner planner, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        plannerUserService.create(planner);
        request.getRequestDispatcher("/login").forward(request, response);
    }

    @GetMapping("/login")
    @HxTriggerAfterSettle("{\"tab-change\": \"login\"}")
    String login(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/login :: content" : "pages/login";
    }

}