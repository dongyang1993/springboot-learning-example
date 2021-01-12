package org.springboot.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 日志中统一打印请求参数
 */
@Slf4j
@Component
public class RequestInfoFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String method = httpServletRequest.getMethod();
        String uri = httpServletRequest.getRequestURI();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(parameterMap);
        log.info("Filter | URI: {} | Method: {} | Params: {}", uri, method, json);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
