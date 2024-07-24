package com.doog.service;

import com.doog.pojo.DTO.UserBaseInfoDTO;
import com.doog.pojo.User;
import com.doog.pojo.VO.UserVO;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    UserVO findByUsernameReturnVO(String username);

    void register(String username, String password);

    void modifyPermission(String username, Integer per, Integer clubId);

    void modifyBaseInfo(UserBaseInfoDTO userBaseInfoDTO);

    void updateAvatar(Long userId, String photoPath);

    void resetPassword(String username, String password);

    User detail(Long userId);
}