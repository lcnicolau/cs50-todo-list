package io.github.lcnicolau.cs50.todolist.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxLocation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import java.io.IOException;
import java.util.Map;

import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequestHeader.HX_BOOSTED;
import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequestHeader.HX_REQUEST;
import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader.HX_LOCATION;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
class HxLocationRedirectStrategy implements RedirectStrategy {

    private final boolean redirectAsBoosted;
    private final HttpStatus status;

    private final RedirectStrategy delegate = new DefaultRedirectStrategy();
    private final JsonMapper jsonMapper = new JsonMapper();

    HxLocationRedirectStrategy() {
        this(true, OK);
    }

    HxLocationRedirectStrategy(boolean redirectAsBoosted) {
        this(redirectAsBoosted, OK);
    }

    HxLocationRedirectStrategy(HttpStatus status) {
        this(true, status);
    }

    HxLocationRedirectStrategy(boolean redirectAsBoosted, HttpStatus status) {
        this.redirectAsBoosted = redirectAsBoosted;
        this.status = status;
    }


    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (request.getHeader(HX_REQUEST.getValue()) == null) {
            delegate.sendRedirect(request, response, url);
        } else {
            this.sendLocationRedirect(request, response, url);
        }
    }

    private void sendLocationRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        log.debug("Redirecting to {} with status code {}", url, status.value());
        var location = redirectAsBoosted ? boosted(url) : url;
        response.setHeader(HX_LOCATION.getValue(), location);
        response.setStatus(status.value());
        response.getWriter().flush();
    }

    private String boosted(String url) throws JsonProcessingException {
        HtmxLocation location = new HtmxLocation(url);
        location.setTarget("body");
        location.setHeaders(Map.of(HX_BOOSTED.getValue(), "true"));
        return jsonMapper.writeValueAsString(location);
    }

}
