package com.doog.service;

import com.doog.pojo.Club;
import com.doog.pojo.PageBean;
import com.doog.pojo.Result;

import java.util.List;

public interface ClubService {
    public Club getClubByName(String name);

    public void addClub(Club club);

    List<Club> findAll();

    void modify(Club club);

    Club detail(Integer clubId);

    void delete(Long id);

    PageBean<Club> pageList(int pageNum, int pageSize);
}
