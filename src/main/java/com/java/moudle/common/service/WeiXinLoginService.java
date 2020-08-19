package com.java.moudle.common.service;

import com.java.moudle.common.message.JsonResult;

import java.net.UnknownHostException;

public interface WeiXinLoginService {
    JsonResult weChatLogin(String code) throws UnknownHostException;
}
