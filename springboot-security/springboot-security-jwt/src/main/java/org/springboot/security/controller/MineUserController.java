package org.springboot.security.controller;

import org.springboot.security.entity.MineUser;
import org.springboot.security.service.MineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MineUserController {

    @Autowired
    private MineUserService mineUserService;

    @GetMapping("/listAll")
    public List<MineUser> listAll() {
        return mineUserService.listAll();
    }
}
