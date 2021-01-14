package org.springboot.security.exception;

import org.springboot.security.common.api.Rs;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public Rs handleValidException(AuthenticationException e) {
        String message = e.getMessage();
        return Rs.failed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Rs handleValidException(Exception e) {
        String message = e.getMessage();
        return Rs.failed(message);
    }
}
