package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.demo.api.WechatMPApi;
import com.example.demo.api.WechatMPSNSApi;
import com.example.demo.constant.WechatConstant;
import com.example.demo.service.WechatMPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WechatMPServiceImpl implements WechatMPService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${wechatmp.appId}")
    private String appId;

    @Value("${wechatmp.appSecret}")
    private String appSecret;

    @Autowired
    private WechatMPApi wechatMPApi;

    @Autowired
    private WechatMPSNSApi wechatMPSNSApi;

    @Override
    public String getAccessToken() {
        logger.info("ready to renew wechatmp access token");
        JSONObject jsonObject = wechatMPApi.getAccessToken("client_credential", appId, appSecret);
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }

    @Override
    public JSONObject getUserAccessToken(String code) {
        String jsonStr = wechatMPSNSApi.getUserAccessToken(appId, appSecret, code, WechatConstant.AUTHORIZATION_CODE);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }
}
