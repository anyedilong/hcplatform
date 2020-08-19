package com.java.moudle.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.service.WeiXinLoginService;
import com.java.moudle.system.dao.SysCustormerDao;
import com.java.moudle.system.dao.SysUserDao;
import com.java.moudle.system.domain.SysCustormer;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.http.HttpUtil;
import com.java.until.wx.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WeiXinLoginServiceImpl implements WeiXinLoginService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${weChatAppId}")
    private String weChatAppId;
    @Value("${weChatSecret}")
    private String weChatSecret;
    @Inject
    private RestTemplate restTemplate;
    @Autowired
    private SysCustormerDao sysCustormerDao;

    //微信登陆
    @Override
    public JsonResult weChatLogin(String code) throws UnknownHostException {
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
        tokenUrl = tokenUrl + "&appid=" + weChatAppId + "&secret=" + weChatSecret + "&code=" + code;

        String resultStr = null;
        try {
            resultStr = HttpUtil.doGet(tokenUrl, null);
            logger.info("微信登陆获取openid返回：" + resultStr);

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (StringUtils.isBlank(resultStr)) {
            return new JsonResult(null, 9001, "微信登陆失败！");
        }
        JSONObject json = JSONObject.parseObject(resultStr);
        String openId = json.getString("openid");
        //String accessToken = json.getString("access_token");
        if (StringUtils.isBlank(openId)) {
            return new JsonResult(null, 9001, "微信登陆失败！");
        }
        SysCustormer query = sysCustormerDao.queryInfoByOpenId(openId);
        Map<String, Object> map = new HashMap<>();
        if (query != null) {
            String url = "http://oa/oa/oauth/token?grant_type=password&username=" + query.getPhone() + "&password=" +
                    query.getPwd() + "&client_id=benefit&client_secret=123456&clientIp=" + InetAddress.getLocalHost().getHostAddress();
            logger.info("获取token请求URL：" + url);
            String jsonStr = restTemplate.getForEntity(url, String.class).getBody();
            JSONObject authJson = JSONObject.parseObject(jsonStr);
            String accessToken = authJson.getString("access_token");
            map.put("accessToken", accessToken);
            map.put("phone", query.getPhone());
            map.put("sfzh", query.getSfzh());
            map.put("name", query.getName());
            map.put("custormerUrl", query.getCustormerUrl());
            return new JsonResult(map, 0, "成功");
        }
        map.put("openId", openId);
        return new JsonResult(map, 1, "成功");
    }

    public void getUserInfo () throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + "34_NJ2GmAnUNRLXODeejvBtPtBksiz6dKJ47oxcyFry_H9IvVfBDQ7YkyjfHgwUNgwpgraqBWmoS114PhegZoOKMA"
                + "&openid=od-TI0iuRLG0ybY1gWOZUIG3i05M" +
                "&lang=zh_CN";
        String resultStr = AuthUtil.doGet(url);
        System.out.println(resultStr);
    }


}
