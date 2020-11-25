package org.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
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


    //单个角色
    @Secured("ROLE_root")
    private String findOne() {
        return "One";
    }

    //多个角色（拥有一个即可）
    @Secured({"ROLE_root", "ROLE_manager"})
    private String findTwo() {
        return "Two";
    }

    @PreAuthorize("hasAnyAuthority('menu:system')")
    private String findThree() {
        return "Three";
    }

    @PreAuthorize("hasAnyRole()")
    private String findFour() {
        return "Three";
    }

    @PreAuthorize("hasPermission('')")
    private String findFive() {
        return "Five";
    }

    @PostAuthorize("hasAnyAuthority('xxxx')")
    private String findSix() {
        return "Six";
    }

    @PreFilter("hasAnyAuthority('xxxx')")
    private String findSeven() {
        return "findSeven";
    }

    @PostFilter("hasAnyAuthority('xxxx')")
    private String findEight() {
        return "Eight";
    }
}
