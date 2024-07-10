package com.doog.mapper;

import com.doog.pojo.Club;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubMapper {

    @Select("select * from t_club where name = #{name}")
    Club findByName(String name);

    @Insert("INSERT INTO t_club (name, info, cate, date) " +
            "VALUES (#{name}, #{info}, #{cate}, now())")
    void add(Club club);

    @Select("select * from t_club")
    List<Club> findAll();
}
