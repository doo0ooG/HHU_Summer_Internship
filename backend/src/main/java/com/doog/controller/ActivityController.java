package com.doog.controller;

import com.doog.pojo.Activity;
import com.doog.pojo.DTO.CommentDTO;
import com.doog.pojo.Enroll;
import com.doog.pojo.Result;
import com.doog.pojo.DTO.ActivityAddDTO;
import com.doog.pojo.VO.ActivityDetailVO;
import com.doog.pojo.VO.ActivityVO;
import com.doog.pojo.VO.CommentVO;
import com.doog.pojo.VO.EnrollVO;
import com.doog.service.ActivityService;
import com.doog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/list")
    public Result<List<ActivityVO>> list() {
        List<ActivityVO> list = activityService.list();
        return Result.success(list);
    }

    // 创建活动
    @PostMapping("/add")
    public Result add(@RequestBody ActivityAddDTO activityAddDTO) {
        Activity activity = new Activity();
        Map<String, Object> map = ThreadLocalUtil.get();

        if (((Integer)map.get("per")).intValue() == 3) {
            return Result.error("权限不够，菜就多练");
        }

        if (map.get("clubId") == null) {
            return Result.error("还没有成为该社团管理人");
        }

        activity.setClubId(((Integer)map.get("clubId")).longValue());
        activity.setUserId(((Integer)map.get("id")).longValue());

//        System.out.println(activityAddDTO.toString());

        activity.setName(activityAddDTO.getName());
        activity.setInfo(activityAddDTO.getInfo());
        activity.setStartDate(activityAddDTO.getStartDate());
        activity.setEndDate(activityAddDTO.getEndDate());
        activity.setPos(activityAddDTO.getPos());
        activity.setCate(activityAddDTO.getCate());
        activity.setLimitNum(activityAddDTO.getLimitNum());
        activity.setPhotoPath(activityAddDTO.getPhotoPath());

//        System.out.println(activity.toString());

        activityService.add(activity);
        return Result.success("创建活动成功");
    }

    @GetMapping("/enroll")
    public Result enroll(Long activityId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = ((Integer)map.get("id")).longValue();
//        System.out.println(activityId + " " + userId);
        // 如果该用户已经报名了该活动
        Enroll enroll = activityService.findEnrollByUserIdAndActivityId(activityId, userId);
        if (enroll != null) {
            return Result.error("已经报名了该活动");
        }
        activityService.enroll(activityId, userId);
        return Result.success();
    }

    @GetMapping("/withdraw")
    public Result withdraw(Long activityId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = ((Integer)map.get("id")).longValue();
        Enroll enroll = activityService.findEnrollByUserIdAndActivityId(activityId, userId);
        if (enroll == null) {
            return Result.error("还未报名该活动");
        }
        activityService.withdraw(activityId, userId);
        return Result.success();
    }

    // 发表评论
    @PostMapping("/comment")
    public Result comment(@RequestBody CommentDTO commentDTO) {
//        System.out.println(commentDTO.toString());
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = ((Integer)map.get("id")).longValue();

        commentDTO.setUserId(userId);
//        System.out.println(commentDTO.toString());
        activityService.comment(commentDTO);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<ActivityDetailVO> detail(Long activityId) {
//        System.out.println(activityId);
        ActivityDetailVO activityDetailVO = activityService.detailById(activityId);
        return Result.success(activityDetailVO);
    }

    @GetMapping("/comment")
    public Result<List<CommentVO>> comment(Long activityId) {
        return Result.success(activityService.commentListById(activityId));
    }

    @GetMapping("/commentList")
    public Result<List<CommentVO>> commentList() {
        List<CommentVO> list = activityService.commentList();
        return Result.success(list);
    }

    @GetMapping("/enrollList")
    public Result<List<EnrollVO>> enrollList() {
        List<EnrollVO> list = activityService.enrollList();
        return Result.success(list);
    }
}
