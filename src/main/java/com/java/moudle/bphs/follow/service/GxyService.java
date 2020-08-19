package com.java.moudle.bphs.follow.service;

import com.java.moudle.common.message.JsonResult;

import java.util.Map;

public interface GxyService {
    JsonResult getGxyDetail(String url, Map<String, String> paramMap);
}
