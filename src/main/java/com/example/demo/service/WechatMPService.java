package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;

public interface WechatMPService {

    String getAccessToken();

    JSONObject getUserAccessToken(String code);

}
