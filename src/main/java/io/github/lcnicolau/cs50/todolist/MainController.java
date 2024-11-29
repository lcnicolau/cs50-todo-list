package io.github.lcnicolau.cs50.todolist;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MainController {

    @GetMapping({"/", "/home"})
    String home(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/home :: content" : "pages/home";
    }

    @GetMapping("/tasks")
    String tasks(HtmxRequest request) {
        return request.isHtmxRequest() ? "pages/tasks :: content" : "pages/tasks";
    }

}