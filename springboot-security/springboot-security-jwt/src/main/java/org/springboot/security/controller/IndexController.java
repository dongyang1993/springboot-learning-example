package org.springboot.security.controller;

import org.springboot.security.common.api.Rs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public Rs index() {
        return Rs.success("INDEX");
    }
}
