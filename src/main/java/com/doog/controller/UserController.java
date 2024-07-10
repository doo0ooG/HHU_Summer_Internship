package com.doog.controller;

import com.doog.pojo.Result;
import com.doog.pojo.User;
import com.doog.pojo.UserDTO;
import com.doog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public Result findAll() {
//        return Result.success(userService.findAll());
        return Result.success(userService.findAll());
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // 查询用户
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error("用户名已存在");
        } else {
            // 注册
            userService.register(username, password);
        }

        return Result.success();
    }


}
