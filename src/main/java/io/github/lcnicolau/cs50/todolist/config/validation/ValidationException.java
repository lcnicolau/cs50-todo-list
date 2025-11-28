package io.github.lcnicolau.cs50.todolist.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class ValidationException extends ResponseStatusException {

    public ValidationException(BindingResult bindingResult) {
        this(resume(bindingResult));
    }

    public ValidationException(String reason) {
        super(HttpStatus.UNPROCESSABLE_CONTENT, reason);
    }

    private static String resume(BindingResult bindingResult) {
        return Stream.concat(
                        bindingResult.getGlobalErrors().stream().map(ObjectError::getDefaultMessage),
                        bindingResult.getFieldErrors().stream().map(ValidationException::getFieldMessage)
                ).map(StringUtils::capitalize)
                .collect(joining(lineSeparator()));
    }

    private static String getFieldMessage(FieldError fieldError) {
        return fieldError.getField() + " " + fieldError.getDefaultMessage();
    }

}
