package com.java.moudle.bphs.follow.service;

import com.java.moudle.common.message.JsonResult;

import java.util.Map;

public interface TnbService {
    JsonResult getTnbDetail(String url, Map<String, String> paramMap);
}
