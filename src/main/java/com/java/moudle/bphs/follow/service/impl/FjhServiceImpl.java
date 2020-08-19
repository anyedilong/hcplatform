package com.java.moudle.bphs.follow.service.impl;

import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.java.moudle.bphs.follow.dto.HcpFjhDto;
import com.java.moudle.bphs.follow.dto.HcpFjhrhDto;
import com.java.moudle.bphs.follow.service.FjhService;
import com.java.moudle.common.message.JsonResult;
import com.java.until.http.HttpUtil;

@Named
public class FjhServiceImpl implements FjhService {


    @Override
    public JsonResult getFjhrhDetail(String url, Map<String, String> paramMap) {
        JsonResult result = HttpUtil.providePost(url, paramMap);
        String data = result.getData().toString();
        HcpFjhrhDto fjhrhDto = JSON.parseObject(data, HcpFjhrhDto.class);
        result.setData(fjhrhDto);
        return result;
    }

    @Override
    public JsonResult getFjhFollowDetail(String url, Map<String, String> paramMap) {
        JsonResult result = HttpUtil.providePost(url, paramMap);
        String data = result.getData().toString();
        HcpFjhDto fjhDto = JSON.parseObject(data, HcpFjhDto.class);
        result.setData(fjhDto);
        return result;
    }

}
