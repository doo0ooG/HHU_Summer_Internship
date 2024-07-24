package com.doog.service.impl;

import com.doog.mapper.ClubMapper;
import com.doog.pojo.Club;
import com.doog.pojo.PageBean;
import com.doog.pojo.Result;
import com.doog.service.ClubService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    @Autowired
    private ClubMapper clubMapper;


    @Override
    public Club getClubByName(String name) {
        return clubMapper.findByName(name);
    }

    @Override
    public void addClub(Club club) {
        clubMapper.add(club);
    }

    @Override
    public List<Club> findAll() {
        return clubMapper.findAll();
    }

    @Override
    public void modify(Club club) {
        clubMapper.modify(club);
    }

    @Override
    public Club detail(Integer clubId) {
        return clubMapper.detail(clubId);
    }

    @Override
    public void delete(Long id) {
        clubMapper.delete(id);
    }

    @Override
    public PageBean<Club> pageList(int pageNum, int pageSize) {
        PageBean<Club> pageBean = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);

        List<Club> list = clubMapper.findAll();
        Page<Club> p = (Page<Club>) list;
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }


}
