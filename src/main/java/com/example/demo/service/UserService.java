package com.example.demo.service;


import com.example.demo.exception.ClientException;
import com.example.demo.po.User;
import com.example.demo.po.UserDetail;
import com.example.demo.vo.Position;

public interface UserService {

    void create(User user, UserDetail userDetail);

    void delete(String openid);

    void savePosition(String openId, Position position);

    Position loadPosition(String openid);

    void checkIn(String openid) throws ClientException;

    void checkOut(String openid) throws ClientException;

    User getUserFromWechatMP(String openId) throws ClientException;
}
