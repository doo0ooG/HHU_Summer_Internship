package com.doog.service;

import com.doog.pojo.Activity;
import com.doog.pojo.DTO.CommentDTO;
import com.doog.pojo.Enroll;
import com.doog.pojo.Result;
import com.doog.pojo.VO.ActivityDetailVO;
import com.doog.pojo.VO.ActivityVO;
import com.doog.pojo.VO.CommentVO;
import com.doog.pojo.VO.EnrollVO;

import java.util.List;

public interface ActivityService {
    List<ActivityVO> list();

    void add(Activity activity);

    void enroll(Long activityId, Long userId);

    Enroll findEnrollByUserIdAndActivityId(Long activityId, Long userId);

    void comment(CommentDTO commentDTO);

    ActivityDetailVO detailById(Long activityId);

    void withdraw(Long activityId, Long userId);


    List<CommentVO> commentListById(Long activityId);

    List<CommentVO> commentList();

    List<EnrollVO> enrollList();
}
