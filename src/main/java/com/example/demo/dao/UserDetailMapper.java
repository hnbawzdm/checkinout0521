package com.example.demo.dao;


import com.example.demo.po.UserDetail;

public interface UserDetailMapper {
    int deleteByPrimaryKey(String openid);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
}