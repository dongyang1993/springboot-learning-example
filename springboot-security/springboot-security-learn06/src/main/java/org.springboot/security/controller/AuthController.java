package org.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springboot.security.entity.MineUser;
import org.springboot.security.service.UserService;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public String user(@ModelAttribute MineUser mineUser) {
        MineUser po = userService.save(mineUser);
        System.out.println(po);
        return po.toString();
    }

    @GetMapping("/check")
    @ResponseBody
    public String check() {
        return "权限验证通过";
    }

    @GetMapping("/find")
    @ResponseBody
    public String find() {
        return this.findOne();
    }

    @Secured("ROLE_root")
    private String findOne() {
        return "One";
    }

}
