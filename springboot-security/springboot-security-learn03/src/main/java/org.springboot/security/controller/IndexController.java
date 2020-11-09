package org.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/index")
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Index Page";
    }

    @GetMapping("/public")
    public String publicView() {
        return "Hello World";
    }
}
