package com.example.demo.handler;


import com.example.demo.constant.WechatEventKeyConstant;
import com.example.demo.dto.TextResMsg;
import com.example.demo.dto.WechatMPClickEventReqMsg;
import com.example.demo.dto.WechatMPEventReqMsg;
import com.example.demo.dto.WechatMPResMsg;
import com.example.demo.exception.ClientException;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
public class ClickEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    public WechatMPResMsg handle(WechatMPClickEventReqMsg reqMsg) throws ClientException {

        WechatMPResMsg resMsg = null;

        String eventKey = reqMsg.getEventKey();

        switch (eventKey) {
            case WechatEventKeyConstant.CHECK_IN:
                logger.info("receive {}", WechatEventKeyConstant.CHECK_IN);
                resMsg = handleCheckIn(reqMsg);
                break;
            case WechatEventKeyConstant.CHECK_OUT:
                logger.info("receive {}", WechatEventKeyConstant.CHECK_OUT);
                resMsg = handleCheckOut(reqMsg);
                break;
            default:
                logger.info("it doesn't match any event key");
        }

        return resMsg;
    }

    private TextResMsg handleCheckIn(WechatMPEventReqMsg reqMsg) throws ClientException {
        @NotBlank String openid = reqMsg.getFromUserName();
        userService.checkIn(openid);
        TextResMsg textResMsg = new TextResMsg(openid, "thx, check in succeed");
        return textResMsg;
    }

    private TextResMsg handleCheckOut(WechatMPEventReqMsg reqMsg) throws ClientException {
        @NotBlank String openid = reqMsg.getFromUserName();
        userService.checkOut(openid);
        TextResMsg textResMsg = new TextResMsg(openid, "thx, check out succeed");
        return textResMsg;
    }
}
