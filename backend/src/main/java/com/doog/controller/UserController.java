package com.doog.controller;

import com.doog.mapper.UserMapper;
import com.doog.pojo.DTO.*;
import com.doog.pojo.Result;
import com.doog.pojo.User;
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


@CrossOrigin
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

    // 修改用户权限
    @PostMapping("/modifyPermission")
    public Result modifyPermission(@RequestBody ModifyPermissionDTO modifyPermissionDTO) {
//        System.out.println(modifyPermissionDTO.toString());
        // 检查是否为admin用户
        Map<String, Object> map = ThreadLocalUtil.get();
        if (!map.get("username").toString().equals("admin")) {
            return Result.error("此操作仅admin可执行，请联系超级管理员钨冬");
        }
        // 获取user
        UserVO user = userService.findByUsernameReturnVO(modifyPermissionDTO.getUsername());
        // 升级
        if (modifyPermissionDTO.getPer().equals(2)) {
            // 并且已经有了所管理的俱乐部
            if (user.getClubName() != null) {
                return Result.error("已经是" + user.getClubName() + "的管理员");
            }
        } else if (modifyPermissionDTO.getPer().equals(3) && modifyPermissionDTO.getClubId() != null) {
            return Result.error("普通用户不能成为社团管理员");
        }
        userService.modifyPermission(modifyPermissionDTO.getUsername(), modifyPermissionDTO.getPer(), modifyPermissionDTO.getClubId());
        return Result.success();
    }

    //用户基本信息修改
    @PostMapping("/modifyBaseInfo")
    public Result modifyBaseInfo(@RequestBody UserBaseInfoDTO userBaseInfoDTO) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = ((Integer)map.get("id")).longValue();
        userBaseInfoDTO.setId(userId);
//        System.out.println(userBaseInfoDTO.toString());

        userService.modifyBaseInfo(userBaseInfoDTO);
        return Result.success();
    }

    //用户头像修改
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestBody AvatarDTO avatarDTO) {
        System.out.println(avatarDTO.getPhotoPath());
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = ((Integer)map.get("id")).longValue();
        userService.updateAvatar(userId, avatarDTO.getPhotoPath());
        return Result.success();
    }

    // 修改密码
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody UserResetPasswordDTO userResetPasswordDTO) {
        String oldPassword = userResetPasswordDTO.getOldPassword();
        String newPassword = userResetPasswordDTO.getNewPassword();
        String username = userResetPasswordDTO.getUsername();
        String trueOldPassword = userService.findByUsername(username).getPassword();
        // System.out.println("hhhhhhhhhhhhhhhhhhh");
        if (!Md5Util.getMD5String(oldPassword).equals(trueOldPassword)) {
            return Result.error("旧密码输入错误");
        }
        // System.out.println("12312312312312312313123123");
        userService.resetPassword(username, Md5Util.getMD5String(newPassword));
//        System.out.println(user.toString());
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<User> detail(Long userId) {
        return Result.success(userService.detail(userId));
    }
}