package io.github.lcnicolau.cs50.todolist;

import io.github.lcnicolau.cs50.todolist.users.User;
import io.github.lcnicolau.cs50.todolist.users.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTriggerAfterSettle;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class MainController {

    private final UserService userService;


    @GetMapping({"/", "/home"})
    @HxTriggerAfterSettle("{\"tab-change\": \"home\"}")
    String home(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/home :: content" : "pages/home";
    }

    @GetMapping("/about")
    @HxTriggerAfterSettle("{\"tab-change\": \"about\"}")
    String about(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/about :: content" : "pages/about";
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
    String signup(@Valid User user) {
        userService.create(user);
        return "forward:/login";
    }

    @GetMapping("/login")
    @HxTriggerAfterSettle("{\"tab-change\": \"login\"}")
    String login(HtmxRequest request) {
        return request.isHtmxRequest() && !request.isBoosted() ? "pages/login :: content" : "pages/login";
    }

}