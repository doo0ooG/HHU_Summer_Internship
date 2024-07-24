package com.doog.service.impl;

import com.doog.mapper.UserMapper;
import com.doog.pojo.DTO.UserBaseInfoDTO;
import com.doog.pojo.User;
import com.doog.pojo.VO.UserVO;
import com.doog.service.UserService;
import com.doog.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        List<User> list = userMapper.getUserList();
        return list;
    }

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByName(username);
        return user;
    }

    @Override
    public UserVO findByUsernameReturnVO(String username) {
        return userMapper.findByUsernameReturnVO(username);
    }

    @Override
    public void register(String username, String password) {
        // MD5加密算法
        String md5Password = Md5Util.getMD5String(password);
        userMapper.register(username, md5Password);
    }

    @Override
    public void modifyPermission(String username, Integer per, Integer clubId) {
        userMapper.modifyPermission(username, per, clubId);
    }

    @Override
    public void modifyBaseInfo(UserBaseInfoDTO userBaseInfoDTO) {
        userMapper.modifyBaseInfo(userBaseInfoDTO);
    }

    @Override
    public void updateAvatar(Long userId, String photoPath) {

        userMapper.updateAvatar(userId, photoPath);
    }

    @Override
    public void resetPassword(String username, String password) {
        userMapper.resetPassword(username, password);
    }

    @Override
    public User detail(Long userId) {
        return userMapper.detail(userId);
    }
}