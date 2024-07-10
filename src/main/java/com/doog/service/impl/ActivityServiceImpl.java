package com.doog.service.impl;

import com.doog.mapper.ActivityMapper;
import com.doog.pojo.Activity;
import com.doog.pojo.VO.ActivityVO;
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
        activityMapper.add(activity);
    }
}
