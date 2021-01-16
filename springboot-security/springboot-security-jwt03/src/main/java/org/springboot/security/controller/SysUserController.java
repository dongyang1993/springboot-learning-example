package org.springboot.security.controller;

import io.swagger.annotations.ApiOperation;
import org.springboot.security.common.api.Rs;
import org.springboot.security.entity.SysUser;
import org.springboot.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService mineUserService;

    @ApiOperation(value = "获取全部用户列表")
    @PreAuthorize("hasAuthority('/user/listAll')")
    @GetMapping("/listAll")
    public Rs<List<SysUser>> listAll() {
        List<SysUser> userList = mineUserService.listAll();
        return Rs.ok(userList);
    }
}
