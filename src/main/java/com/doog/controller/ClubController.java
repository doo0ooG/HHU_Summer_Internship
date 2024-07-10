package com.doog.controller;

import com.doog.pojo.Club;
import com.doog.pojo.Result;
import com.doog.service.ClubService;
import com.doog.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private ClubService clubService;

    // 新建社团，需要超级管理员才可以添加
    @RequestMapping("/add")
    public Result add(@RequestBody Club newClub) {
//        System.out.println(newClub.toString());
        Map<String, Object> map = ThreadLocalUtil.get();
        if (!map.get("per").equals(1)) {
            return Result.error("仅超级管理员可以新建社团");
        }
        // 查询是否有同名俱乐部
        Club club = clubService.getClubByName(newClub.getName());
        if (club != null) {
            return Result.error("该社团已经存在或者重名");
        }

        // 添加到数据库中
        clubService.addClub(newClub);
        return Result.success("添加成功");
    }

    @RequestMapping("/list")
    public Result<List<Club>> findAll() {
        return Result.success(clubService.findAll());
    }
}
