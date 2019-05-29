package com.example.demo.dao;


import com.example.demo.po.CheckRecord;
import org.apache.ibatis.annotations.Param;

public interface CheckRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckRecord record);

    int insertSelective(CheckRecord record);

    CheckRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckRecord record);

    int updateByPrimaryKey(CheckRecord record);

//    custom api
    int deleteByOpenid(@Param("openid") String openid);
}