package com.java.moudle.common.controller;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.service.WeiXinLoginService;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.service.SysCustormerService;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.until.wx.AuthUtil;

import javax.inject.Inject;

/**
 * <br>
 * <b>功能：</b>CustomerController<br>
 * <b>作者：</b>blt<br>
 * <b>版权所有：<b>版权所有(C) 2016，blt<br>
 */
@RestController
@RequestMapping("wxlogin")
public class WeiXinLoginController extends BaseController {

    private static String wxOpenAppId = "wx51748679de046333";
    private static String wxOpenAppSecret = "576441caf3e1393dec24169922822000";
    private static String redirectUrl = "http://123.233.244.218:8084/wxlogin/getTDC";
    @Autowired
    private WeiXinLoginService weiXinLoginService;

    /**
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     * @Description: 返回微信二维码，可供扫描登录
     */
    @RequestMapping(value = "/getTDC")
    public JsonResult wxLogin(String code) {
        try {

            String param = getParam(request);

            //String[] s = getOpenId(code);
            // System.out.println(s[1]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 微信登陆
     * @return
     * 聂亚威
     */
    @RequestMapping("weChatLogin")
    public JsonResult weChatLogin() throws UnknownHostException {
        String param = getParam(request);
        logger.info("微信登陆请求参数：" + param);
        JSONObject json = JSONObject.parseObject(param);
        if (json.size() == 0) {
            return new JsonResult(null, 9001, "参数不能为空！");
        }
        String code = json.getString("code");
        if (StringUtils.isBlank(code)) {
            return new JsonResult(null, 9001, "code不能为空！");
        }
        JsonResult result = weiXinLoginService.weChatLogin(code);
        logger.info("微信登陆返回：" + JSON.toJSONString(result));
        return result;
    }



    /**
     * @param @param  redirect_uri
     * @param @return
     * @return String
     * @throws
     * @Description: 获取code请求地址
     */
    public String getCodeUrl(String redirect_uri) {
        redirect_uri = URLEncoder.encode(redirect_uri);   //使用 urlEncode 对链接进行处理
        String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
        codeUrl = codeUrl + "?appid=" + wxOpenAppId + "&redirect_uri=" + redirect_uri
                + "&response_type=code&scope=snsapi_userinfo&state=jetcms#wechat_redirect";
        return codeUrl;
    }
}
