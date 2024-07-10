package com.doog.service;

import com.doog.pojo.Activity;
import com.doog.pojo.VO.ActivityVO;

import java.util.List;

public interface ActivityService {
    List<ActivityVO> list();

    void add(Activity activity);
}
