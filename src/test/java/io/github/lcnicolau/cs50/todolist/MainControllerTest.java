package io.github.lcnicolau.cs50.todolist;

import io.github.lcnicolau.cs50.todolist.config.SecurityConfig;
import io.github.lcnicolau.cs50.todolist.users.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.stream.Stream;

import static io.github.lcnicolau.cs50.todolist.HtmxUtils.boosted;
import static io.github.lcnicolau.cs50.todolist.HtmxUtils.htmx;
import static java.util.stream.Stream.of;
import static org.jsoup.Jsoup.parse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
@Import(SecurityConfig.class)
class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private ErrorAttributes errorAttributes;


    @ParameterizedTest
    @MethodSource("allowedRequests")
    void requestsWithoutHtmxRespondWithFullPage(String pathname, RequestPostProcessor security) throws Exception {
        var content = mvc.perform(get(pathname).with(security))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var header = parse(content).selectFirst("html>body>header");
        assertNotNull(header, "Response should contain <header>");
    }

    @ParameterizedTest
    @MethodSource("allowedRequests")
    void requestsWithHtmxBoostedRespondWithFullPage(String pathname, RequestPostProcessor security) throws Exception {
        var content = mvc.perform(get(pathname).with(security).with(htmx()).with(boosted()))
                .andExpect(header().exists("HX-Trigger-After-Settle"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var header = parse(content).selectFirst("html>body>header");
        assertNotNull(header, "Response should contain <header>");
    }

    @ParameterizedTest
    @MethodSource("allowedRequests")
    void requestsWithHtmxRespondWithPartialContent(String pathname, RequestPostProcessor security) throws Exception {
        var content = mvc.perform(get(pathname).with(security).with(htmx()))
                .andExpect(header().exists("HX-Trigger-After-Settle"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var header = parse(content).selectFirst("html>body>header");
        assertNull(header, "Response should not contain <header>");
    }


    @ParameterizedTest
    @MethodSource({"unauthorizedRequests", "forbiddenRequests"})
    void requestsWithoutHtmxRespondWithRedirection(String pathname, RequestPostProcessor security) throws Exception {
        mvc.perform(get(pathname).with(security))
                .andExpect(status().isFound())
                .andExpect(header().exists("Location"))
                .andExpect(content().string(""));
    }

    @ParameterizedTest
    @MethodSource("unauthorizedRequests")
    void requestsWithHtmxRespondWithHxLocationUnauthorized(String pathname, RequestPostProcessor security) throws Exception {
        mvc.perform(get(pathname).with(security).with(htmx()))
                .andExpect(status().isUnauthorized())
                .andExpect(header().exists("HX-Location"))
                .andExpect(content().string(""));
    }

    @ParameterizedTest
    @MethodSource("forbiddenRequests")
    void requestsWithHtmxRespondWithHxLocationForbidden(String pathname, RequestPostProcessor security) throws Exception {
        mvc.perform(get(pathname).with(security).with(htmx()))
                .andExpect(status().isForbidden())
                .andExpect(header().exists("HX-Location"))
                .andExpect(content().string(""));
    }


    private static Stream<Arguments> allowedRequests() {
        return of(
                arguments("/", anonymous()),
                arguments("/home", anonymous()),
                arguments("/about", anonymous()),
                arguments("/signup", anonymous()),
                arguments("/login", anonymous()),
                arguments("/tasks", user("user").roles("USER")),
                arguments("/users", user("admin").roles("ADMIN")));
    }

    private static Stream<Arguments> unauthorizedRequests() {
        return of(
                arguments("/tasks", anonymous()),
                arguments("/users", anonymous()));
    }

    private static Stream<Arguments> forbiddenRequests() {
        return of(arguments("/users", user("user").roles("USER")));
    }

}