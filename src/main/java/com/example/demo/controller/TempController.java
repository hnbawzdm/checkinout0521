package com.example.demo.controller;


import com.example.demo.exception.ClientException;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import com.example.demo.service.WechatMPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WechatMPService wechatMPService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() throws ClientException {
//        String accessToken = wechatMPService.getAccessToken();
        User user = userService.getUserFromWechatMP("odUdT5uxVE2wsFfpZSyAuTreaT8E");
        return "temptest";
    }
}
