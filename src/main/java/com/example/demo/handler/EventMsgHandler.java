package com.example.demo.handler;

import com.alibaba.fastjson.JSON;

import com.example.demo.constant.WechatConstant;
import com.example.demo.constant.WechatEventConstant;
import com.example.demo.dto.TextResMsg;
import com.example.demo.dto.WechatMPClickEventReqMsg;
import com.example.demo.dto.WechatMPEventReqMsg;
import com.example.demo.exception.ClientException;
import com.example.demo.po.User;
import com.example.demo.po.UserDetail;
import com.example.demo.service.UserService;
import com.example.demo.vo.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
public class EventMsgHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ClickEventHandler clickEventHandler;

    public Object handle(WechatMPEventReqMsg reqMsg) throws ClientException {
        Object resMsg = WechatConstant.SUCCESS_RESPONSE;

        String event = reqMsg.getEvent();

        switch (event) {
            case WechatEventConstant.SUBSCRIBE:
                logger.info("receive {}", WechatEventConstant.SUBSCRIBE);
                resMsg = handleSubscribe(reqMsg);
                break;
            case WechatEventConstant.UNSUBSCRIBE:
                logger.info("receive {}", WechatEventConstant.UNSUBSCRIBE);
                handleUnsubscribe(reqMsg);
                break;
            case WechatEventConstant.SCAN:
                logger.info("receive {}", WechatEventConstant.SCAN);
                break;
            case WechatEventConstant.LOCATION:
                logger.info("receive {}", WechatEventConstant.LOCATION);
                handleLocation(reqMsg);
                break;
            case WechatEventConstant.CLICK:
                logger.info("receive {}", WechatEventConstant.CLICK);
                String reqMsgJsonStr = reqMsg.toJSONString();
                WechatMPClickEventReqMsg clickEventReqMsg = JSON.parseObject(reqMsgJsonStr, WechatMPClickEventReqMsg.class);
                resMsg = clickEventHandler.handle(clickEventReqMsg);
                break;
            case WechatEventConstant.VIEW:
                logger.info("receive {}", WechatEventConstant.VIEW);
                break;
            default:
                logger.info("it doesn't match any event");
        }
        return resMsg;
    }

    private TextResMsg handleSubscribe(WechatMPEventReqMsg reqMsg) throws ClientException {
        @NotBlank String openid = reqMsg.getFromUserName();
        User user = userService.getUserFromWechatMP(openid);
        UserDetail userDetail = new UserDetail(openid);
        userService.create(user, userDetail);

        String text = String.format("你好，%s，欢迎订阅", user.getNickname());
        TextResMsg textResMsg = new TextResMsg(openid, text);
        return textResMsg;
    }

    public void handleUnsubscribe(WechatMPEventReqMsg reqMsg){
        @NotBlank String openid = reqMsg.getFromUserName();
        userService.delete(openid);
    }

    private void handleLocation(WechatMPEventReqMsg reqMsg){
        @NotBlank String openId = reqMsg.getFromUserName();
        Double latitude = reqMsg.getDouble("Latitude");
        Double longitude = reqMsg.getDouble("Longitude");
        Position position = new Position(latitude, longitude);
        userService.savePosition(openId, position);
        logger.info("set user position: {}, {}", openId, JSON.toJSONString(position));
    }

}
