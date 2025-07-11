package io.github.lcnicolau.cs50.todolist;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxReswap;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader.HX_RESWAP;
import static io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponseHeader.HX_TRIGGER_AFTER_SETTLE;
import static jakarta.servlet.RequestDispatcher.*;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.MULTI_STATUS;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.security.web.WebAttributes.ACCESS_DENIED_403;
import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

@Controller
@ControllerAdvice
class ErrorController extends BasicErrorController {

    ErrorController(ErrorAttributes errorAttributes,
                    ServerProperties serverProperties,
                    ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers.orderedStream().toList());
    }


    @HxRequest
    @RequestMapping
    @ResponseStatus(MULTI_STATUS)
    ModelAndView errorHtmx(HttpServletRequest request, HttpServletResponse response) {
        var model = getErrorAttributes(request, getErrorAttributeOptions(request, TEXT_HTML));
        if (HtmxRequest.fromRequest(request).isBoosted()) {
            response.setHeader(HX_TRIGGER_AFTER_SETTLE.getValue(), "{\"tab-change\": \"error\"}");
            return new ModelAndView("error", model);
        } else {
            response.setHeader(HX_RESWAP.getValue(), HtmxReswap.none().toHeaderValue());
            return new ModelAndView("error :: content", model);
        }
    }


    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, ErrorAttributeOptions options) {
        if (request.getAttribute(AUTHENTICATION_EXCEPTION) instanceof AuthenticationException ex) {
            request.setAttribute(ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
            request.setAttribute(ERROR_EXCEPTION, ex);
        } else if (request.getAttribute(ACCESS_DENIED_403) instanceof AccessDeniedException ex) {
            request.setAttribute(ERROR_STATUS_CODE, HttpStatus.FORBIDDEN.value());
            request.setAttribute(ERROR_EXCEPTION, ex);
        } else if (request.getParameter("unauthorized") != null) {
            request.setAttribute(ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
            request.setAttribute(ERROR_MESSAGE, "Please log in to access this resource");
        } else if (request.getParameter("forbidden") != null) {
            request.setAttribute(ERROR_STATUS_CODE, HttpStatus.FORBIDDEN.value());
            request.setAttribute(ERROR_MESSAGE, "It looks like you don't have access to this resource");
        }
        return super.getErrorAttributes(request, options);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    void handle(MethodArgumentNotValidException ex, HttpServletResponse response) throws IOException {
        var message = ex.getAllErrors().stream()
                .map(error -> (error instanceof FieldError fieldError)
                        ? fieldError.getField() + " " + error.getDefaultMessage()
                        : error.getDefaultMessage())
                .collect(joining(lineSeparator()));
        response.sendError(UNPROCESSABLE_ENTITY.value(), message);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    void handle(DuplicateKeyException ex, HttpServletResponse response) throws IOException {
        response.sendError(UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

}