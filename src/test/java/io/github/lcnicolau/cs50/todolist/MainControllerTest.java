package io.github.lcnicolau.cs50.todolist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.github.lcnicolau.cs50.todolist.HtmxUtils.htmx;
import static org.hamcrest.core.StringContains.containsString;
import static org.jsoup.Jsoup.parse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @ValueSource(strings = {"/", "/home", "/tasks"})
    void requestsWithoutHtmxRespondWithFullPage(String pathname) throws Exception {
        var content = mvc.perform(get(pathname))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var header = parse(content).selectFirst("html>body>header");
        assertNotNull(header, "Response should contain <header>");
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/home", "/tasks"})
    void requestsWithHtmxRespondWithPartialContent(String pathname) throws Exception {
        var content = mvc.perform(get(pathname).with(htmx()))
                .andExpect(header().string("HX-Trigger-After-Settle", containsString("tab-change")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        var header = parse(content).selectFirst("html>body>header");
        assertNull(header, "Response should not contain <header>");
    }

}