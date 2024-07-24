package com.doog.service.impl;

import com.doog.mapper.ActivityMapper;
import com.doog.pojo.Activity;
import com.doog.pojo.DTO.CommentDTO;
import com.doog.pojo.Enroll;
import com.doog.pojo.Result;
import com.doog.pojo.VO.ActivityDetailVO;
import com.doog.pojo.VO.ActivityVO;
import com.doog.pojo.VO.CommentVO;
import com.doog.pojo.VO.EnrollVO;
import com.doog.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<ActivityVO> list() {
        List<ActivityVO> list = activityMapper.list();
        return list;
    }

    @Override
    public void add(Activity activity) {
//        System.out.println(activity.toString());
        activityMapper.add(activity);
    }

    @Override
    public void enroll(Long activityId, Long userId) {
        activityMapper.enroll(activityId, userId);
    }

    @Override
    public Enroll findEnrollByUserIdAndActivityId(Long activityId, Long userId) {
//        System.out.println(activityId + " " + userId);
        Enroll enroll = activityMapper.findEnrollByUserIdAndActivityId(activityId, userId);
        return enroll;
    }

    @Override
    public void comment(CommentDTO commentDTO) {
        activityMapper.comment(commentDTO);
    }

    @Override
    public ActivityDetailVO detailById(Long activityId) {
        ActivityDetailVO activityDetailVO = activityMapper.detailById(activityId);
        return activityDetailVO;
    }

    @Override
    public void withdraw(Long activityId, Long userId) {
        activityMapper.withdraw(activityId, userId);
    }

    @Override
    public List<CommentVO> commentListById(Long activityId) {
        return activityMapper.commentListById(activityId);
    }

    @Override
    public List<CommentVO> commentList() {
        return activityMapper.commentList();
    }

    @Override
    public List<EnrollVO> enrollList() {
        return activityMapper.enrollList();
    }


}
