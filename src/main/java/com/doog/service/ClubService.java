package com.doog.service;

import com.doog.pojo.Club;

import java.util.List;

public interface ClubService {
    public Club getClubByName(String name);

    public void addClub(Club club);

    List<Club> findAll();
}
