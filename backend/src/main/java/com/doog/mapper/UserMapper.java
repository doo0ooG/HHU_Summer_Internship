package com.doog.mapper;

import com.doog.pojo.DTO.UserBaseInfoDTO;
import com.doog.pojo.User;
import com.doog.pojo.VO.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
//    @Select("select * from t_user")
//    List<User> getUserList();
    List<User> getUserList();

//    @Select("select * from t_user where username=#{username}")
    User findByName(String username);

//    @Insert("insert into t_user(username, password) values(#{username}, #{password})")
    void register(String username, String password);


//    @Select("SELECT u.id, u.username, u.sex, u.tel, u.per, u.motto, u.grade, u.photo_path, c.name AS club_name " +
//            "FROM t_user u " +
//            "LEFT JOIN t_club c ON u.club_id = c.id " +
//            "WHERE u.username = #{username}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "username", column = "username"),
//            @Result(property = "sex", column = "sex"),
//            @Result(property = "tel", column = "tel"),
//            @Result(property = "per", column = "per"),
//            @Result(property = "motto", column = "motto"),
//            @Result(property = "grade", column = "grade"),
//            @Result(property = "club_name", column = "clubName"),
//            @Result(property = "photo_path", column = "photoPath")
//    })
//    UserVO findByUsernameReturnVO(String username);

    UserVO findByUsernameReturnVO(@Param("username") String username);

//    @Update("UPDATE t_user SET per = #{per}, club_id = #{clubId} WHERE username = #{username}")
    void modifyPermission(String username, Integer per, Integer clubId);

//    @Update("UPDATE t_user SET sex = #{sex}, tel = #{tel}, motto = #{motto}, grade = #{grade} WHERE id = #{id}")
    void modifyBaseInfo(UserBaseInfoDTO userBaseInfoDTO);

    void updateAvatar(Long userId, String photoPath);

    void resetPassword(String username, String password);

    User detail(Long userId);
}