package io.github.lcnicolau.cs50.todolist;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTriggerAfterSettle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MainController {

    @GetMapping({"/", "/home"})
    @HxTriggerAfterSettle("{\"tab-change\": \"home\"}")
    String home(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/home :: content" : "pages/home";
    }

    @GetMapping("/tasks")
    @HxTriggerAfterSettle("{\"tab-change\": \"tasks\"}")
    String tasks(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/tasks :: content" : "pages/tasks";
    }

    @GetMapping("/signup")
    @HxTriggerAfterSettle("{\"tab-change\": \"signup\"}")
    String signUp(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/signup :: content" : "pages/signup";
    }

    @GetMapping("/login")
    @HxTriggerAfterSettle("{\"tab-change\": \"login\"}")
    String login(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/login :: content" : "pages/login";
    }

}