package com.doog.controller;

import com.doog.pojo.Activity;
import com.doog.pojo.Result;
import com.doog.pojo.DTO.ActivityAddDTO;
import com.doog.pojo.VO.ActivityVO;
import com.doog.service.ActivityService;
import com.doog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            return Result.error("还没有成为某社团管理人");
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

//        System.out.println(activity.toString());

        activityService.add(activity);
        return Result.success("创建活动成功");
    }

}
