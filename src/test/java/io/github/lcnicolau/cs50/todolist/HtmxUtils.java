package io.github.lcnicolau.cs50.todolist;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HtmxUtils {

    static RequestPostProcessor htmx() {
        return request -> {
            request.addHeader("HX-Request", "true");
            return request;
        };
    }

    static RequestPostProcessor boosted() {
        return request -> {
            request.addHeader("HX-Boosted", "true");
            return request;
        };
    }

}