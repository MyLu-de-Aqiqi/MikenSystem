package com.miken.sys.login.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.miken.sys.login.service.AdminService;
import com.miken.sys.util.CommRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/login")
    public CommRes login(String username, String password){
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
            return CommRes.failed("必填参数不能为空");
        }
        return adminService.login(username,password);
    }

    @GetMapping("/tokenVerify")
    public CommRes tokenVerify() {
        boolean login = StpUtil.isLogin();
        if (login){
            return CommRes.ok("","OK");
        }else {
            return CommRes.ok("","FAIL");
        }
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public CommRes logout() {
        // 1、验证参数
        StpUtil.logout();
        return CommRes.ok();
    }

}
