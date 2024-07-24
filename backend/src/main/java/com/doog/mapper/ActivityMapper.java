package com.doog.mapper;

import com.doog.pojo.Activity;
import com.doog.pojo.DTO.CommentDTO;
import com.doog.pojo.Enroll;
import com.doog.pojo.VO.ActivityDetailVO;
import com.doog.pojo.VO.ActivityVO;
import com.doog.pojo.VO.CommentVO;
import com.doog.pojo.VO.EnrollVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityMapper {
//    @Select("SELECT " +
//            "a.id AS id, " +
//            "a.name AS name, " +
//            "a.info AS info, " +
//            "a.start_date AS start_date, " +
//            "a.end_date AS end_date, " +
//            "a.pos AS pos, " +
//            "a.cate AS cate, " +
//            "a.limit_num AS limit_num, " +
//            "u.username AS host_username, " +
//            "c.name AS club_name " +
//            "FROM t_activity a " +
//            "LEFT JOIN t_user u ON a.user_id = u.id " +
//            "LEFT JOIN t_club c ON a.club_id = c.id")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "club_name", column = "clubName"),
//            @Result(property = "info", column = "info"),
//            @Result(property = "host_username", column = "hostUsername"),
//            @Result(property = "start_date", column = "startDate"),
//            @Result(property = "end_date", column = "endDate"),
//            @Result(property = "pos", column = "pos"),
//            @Result(property = "cate", column = "cate"),
//            @Result(property = "limit_num", column = "limitNum")
//    })
    List<ActivityVO> list();

//    @Insert("INSERT INTO t_activity (name, club_id, info, user_id, start_date, end_date, pos, cate, limit_num) " +
//            "VALUES (#{name}, #{clubId}, #{info}, #{userId}, #{startDate}, #{endDate}, #{pos}, #{cate}, #{limitNum})")
    void add(Activity activity);

//    @Insert("INSERT INTO t_enroll(user_id, activity_id, date) VALUES(#{userId}, #{activityId}, now())")
    void enroll(Long activityId, Long userId);

//    @Select("select * from t_enroll where user_id = #{userId} and activity_id = #{activityId}")
    Enroll findEnrollByUserIdAndActivityId(Long activityId, Long userId);

//    @Insert("INSERT INTO t_remark(user_id, activity_id, info, date) VALUES(#{userId}, #{activityId}, #{comment}, now())")
    void comment(CommentDTO commentDTO);

    ActivityDetailVO detailById(Long activityId);

    void withdraw(Long activityId, Long userId);

    List<CommentVO> commentListById(Long activityId);

    List<CommentVO> commentList();

    List<EnrollVO> enrollList();
}
