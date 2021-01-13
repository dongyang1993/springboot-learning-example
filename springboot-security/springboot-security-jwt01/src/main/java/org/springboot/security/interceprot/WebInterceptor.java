package org.springboot.security.interceprot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * HandlerInterceptor是拦截器
 * preHandle发生在Filter之后，进入到Controller之前，可以做一些预处理
 * postHandle发生在Controller即将返回 渲染视图之前
 * afterCompletion在Controller执行完毕，结束得时候
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(parameterMap);
        log.info("Interceptor | URI: {} | Method: {} | Params: {}", uri, method, json);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
