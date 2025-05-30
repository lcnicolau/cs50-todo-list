package io.github.lcnicolau.cs50.todolist;

import io.github.lcnicolau.cs50.todolist.users.User;
import io.github.lcnicolau.cs50.todolist.users.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTriggerAfterSettle;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static java.util.Optional.ofNullable;

@Controller
@RequiredArgsConstructor
class MainController {

    private final UserService userService;
    private final ErrorAttributes errorAttributes;


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
    void signup(@Valid User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.create(user);
        request.getRequestDispatcher("/login").forward(request, response);
    }

    @GetMapping("/login")
    @HxTriggerAfterSettle("{\"tab-change\": \"login\"}")
    ModelAndView login(HttpServletRequest request) {
        var htmx = HtmxRequest.fromRequest(request);
        var view = htmx.isHtmxRequest() && !htmx.isBoosted() ? "pages/login :: content" : "pages/login";
        var error = ofNullable(errorAttributes.getError(new ServletWebRequest(request)));
        return new ModelAndView(view, "message", error.map(Throwable::getMessage).orElse(""));
    }

}