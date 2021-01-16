package org.springboot.security.exception;

import org.springboot.security.common.api.Rs;
import org.springframework.security.access.AccessDeniedException;
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
        return Rs.unauthorized(message);
    }

    /**
     * 会拦截处理进入到Controller时候的AccessDeniedException
     * 比如：@PreAuthorize @Secured 注解控制权限的
     * 没进入到Controller在过滤器中就过滤掉的，就进不来了
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AccessDeniedException.class)
    public Rs handleValidException(AccessDeniedException e) {
        return Rs.forbidden();
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Rs handleValidException(Exception e) {
        String message = e.getMessage();
        return Rs.failed(message);
    }
}
