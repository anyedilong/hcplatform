package com.java.moudle.bphs.follow.service;

import com.java.moudle.common.message.JsonResult;

import java.util.Map;

public interface FjhService {

    JsonResult getFjhrhDetail(String url, Map<String, String> paramMap);

    JsonResult getFjhFollowDetail(String url, Map<String, String> paramMap);

}
