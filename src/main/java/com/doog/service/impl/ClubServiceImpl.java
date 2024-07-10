package com.doog.service.impl;

import com.doog.mapper.ClubMapper;
import com.doog.pojo.Club;
import com.doog.service.ClubService;
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
}
