package org.springboot.security.controller;

import org.springboot.security.common.api.Rs;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 如果需要让404 403等请求进来，并拦截到请求就开启这个Controller
 * 请求一个不存在的URL, Tomcat会进行预处理，将请求forward到/error
 */
//@RestController
public class JwtErrorController implements ErrorController {


    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Rs error(HttpServletRequest request, HttpServletResponse response) {
        Object a1 = request.getAttribute("javax.servlet.forward.request_uri");
        Object a2 = request.getAttribute("javax.servlet.forward.context_path");
        Object a3 = request.getAttribute("javax.servlet.forward.servlet_path");
        Object a4 = request.getAttribute("javax.servlet.forward.path_info");
        Object a5 = request.getAttribute("javax.servlet.error.servlet_name");
        Object a6 = request.getAttribute("javax.servlet.error.message");
        Object a7 = request.getAttribute("javax.servlet.forward.request_uri");
        int statusCode = (int) request.getAttribute("javax.servlet.error.status_code");
        return Rs.failed();
    }
}
