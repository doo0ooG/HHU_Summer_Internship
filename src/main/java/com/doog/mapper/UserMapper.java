package com.doog.mapper;

import com.doog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    public List<User> getUserList();

    @Select("select * from t_user where username=#{username}")
    User findByName(String username);

    @Insert("insert into t_user(username, password) values(#{username}, #{password})")
    void register(String username, String password);
}
