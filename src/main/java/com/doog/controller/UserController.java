package com.doog.controller;

import com.doog.pojo.Result;
import com.doog.pojo.User;
import com.doog.pojo.DTO.UserDTO;
import com.doog.pojo.VO.UserVO;
import com.doog.service.UserService;
import com.doog.utils.JwtUtil;
import com.doog.utils.Md5Util;
import com.doog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    // 正则表达式定义
    private static final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9]{4,15}$"; // 英文开头，只能由英文和数字组成，长度5-20
    private static final String PASSWORD_PATTERN = "^.{5,16}$"; // 长度6-20的任意字符

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

        // 校验用户名格式和长度
        if (!Pattern.matches(USERNAME_PATTERN, username)) {
            return Result.error("用户名格式不正确，必须由英文开头，并且只能包含英文和数字，长度在5-16之间");
        }

        // 校验密码长度
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            return Result.error("密码长度必须在5-16之间");
        }

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

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // 校验用户名格式和长度
        if (!Pattern.matches(USERNAME_PATTERN, username)) {
            return Result.error("用户名格式不正确，必须由英文开头，并且只能包含英文和数字，长度在5-16之间");
        }

        // 校验密码长度
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            return Result.error("密码长度必须在5-16之间");
        }

        // 查询用户
        User user = userService.findByUsername(username);
        if (user != null) {
            if (Md5Util.getMD5String(password).equals(user.getPassword())) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", user.getId());
                map.put("username", user.getUsername());
                map.put("clubId", user.getClubId());
                map.put("per", user.getPer());
                String token = JwtUtil.genToken(map);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
        return Result.error("用户不存在");
    }

    // 返回当前用户的详细信息
    @GetMapping("/userinfo")
    public Result userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        UserVO userVO = userService.findByUsernameReturnVO(username);
        return Result.success(userVO);
    }


}