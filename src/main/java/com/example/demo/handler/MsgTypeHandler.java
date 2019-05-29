package com.example.demo.handler;

import com.alibaba.fastjson.JSON;

import com.example.demo.constant.WechatConstant;
import com.example.demo.constant.WechatReqMsgTypeConstant;
import com.example.demo.dto.WechatMPEventReqMsg;
import com.example.demo.dto.WechatMPReqMsg;
import com.example.demo.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
public class MsgTypeHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventMsgHandler eventMsgHandler;

    public Object handle(WechatMPReqMsg reqMsg) throws ClientException {

        Object resMsg = WechatConstant.SUCCESS_RESPONSE;

        @NotBlank String msgType = reqMsg.getMsgType();
        switch (msgType) {
            case WechatReqMsgTypeConstant.TEXT:
                logger.info("receive {}", WechatReqMsgTypeConstant.TEXT);
                break;
            case WechatReqMsgTypeConstant.IMAGE:
                logger.info("receive {}", WechatReqMsgTypeConstant.IMAGE);
                break;
            case WechatReqMsgTypeConstant.VOICE:
                logger.info("receive {}", WechatReqMsgTypeConstant.VOICE);
                break;
            case WechatReqMsgTypeConstant.VIDEO:
                logger.info("receive {}", WechatReqMsgTypeConstant.VIDEO);
                break;
            case WechatReqMsgTypeConstant.SHORT_VIDEO:
                logger.info("receive {}", WechatReqMsgTypeConstant.SHORT_VIDEO);
                break;
            case WechatReqMsgTypeConstant.LOCATION:
                logger.info("receive {}", WechatReqMsgTypeConstant.LOCATION);
                break;
            case WechatReqMsgTypeConstant.LINK:
                logger.info("receive {}", WechatReqMsgTypeConstant.LINK);
                break;
            case WechatReqMsgTypeConstant.EVENT:
                logger.info("receive {}", WechatReqMsgTypeConstant.EVENT);
                String reqMsgJsonStr = reqMsg.toJSONString();
                WechatMPEventReqMsg eventReqMsg = JSON.parseObject(reqMsgJsonStr, WechatMPEventReqMsg.class);
                resMsg = eventMsgHandler.handle(eventReqMsg);
                break;
            default:
                logger.warn("it doesn't match msg type");
        }
        return resMsg;
    }
}
