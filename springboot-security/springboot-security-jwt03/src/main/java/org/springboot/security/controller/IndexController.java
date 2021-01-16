package org.springboot.security.controller;

import org.springboot.security.common.api.Rs;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public Rs index() {
        return Rs.ok("INDEX");
    }
}
