package com.java.moudle.bphs.follow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.java.moudle.bphs.follow.dto.HcpMbDrugDto;
import com.java.moudle.bphs.follow.dto.HcpMxTnbDto;
import com.java.moudle.bphs.follow.dto.Mxtnbsfb;
import com.java.moudle.bphs.follow.service.TnbService;
import com.java.moudle.common.message.JsonResult;
import com.java.until.StringUtils;
import com.java.until.ToJavaUtils;
import com.java.until.http.HttpUtil;

@Named
public class TnbServiceImpl implements TnbService {

    @Override
    public JsonResult getTnbDetail(String url, Map<String, String> paramMap) {
        JsonResult result = HttpUtil.providePost(url, paramMap);
        String data = result.getData().toString();
        Mxtnbsfb follow = JSON.parseObject(data, Mxtnbsfb.class);
        HcpMxTnbDto tnbDto = new HcpMxTnbDto();
        ToJavaUtils.copyFields(follow, tnbDto);
        tnbDto.setSfys(follow.getSfysmc());
        List<HcpMbDrugDto> drugList = new ArrayList<>();
        HcpMbDrugDto drugDto = new HcpMbDrugDto();
        try{
            //目前用药
            if (!StringUtils.isNull(follow.getYwmc1())) {
                HcpMbDrugDto drugDto1 = (HcpMbDrugDto) drugDto.clone();
                drugDto1.setYwmc(follow.getYwmc1()); // 用药名称
                drugDto1.setYwjl(follow.getYwmc1mg());//剂量
                drugDto1.setYwdw(follow.getZdyydw1()); // 单次单位
                drugDto1.setYwpc(follow.getYwmc1ci()); // 用药频次
                drugDto1.setSfid(follow.getId());// 随访id
                drugList.add(drugDto1);
            }
            if (!StringUtils.isNull(follow.getYwmc2())) {
                HcpMbDrugDto drugDto2 = (HcpMbDrugDto) drugDto.clone();
                drugDto2.setYwmc(follow.getYwmc2()); // 用药名称
                drugDto2.setYwjl(follow.getYwmc2mg());//剂量
                drugDto2.setYwdw(follow.getZdyydw2()); // 单次单位
                drugDto2.setYwpc(follow.getYwmc2ci()); // 用药频次
                drugDto2.setSfid(follow.getId());// 随访id
                drugList.add(drugDto2);
            }
            if (!StringUtils.isNull(follow.getYwmc3())) {
                HcpMbDrugDto drugDto3 = (HcpMbDrugDto) drugDto.clone();
                drugDto3.setYwmc(follow.getYwmc3()); // 用药名称
                drugDto3.setYwjl(follow.getYwmc3mg());//剂量
                drugDto3.setYwdw(follow.getZdyydw3()); // 单次单位
                drugDto3.setYwpc(follow.getYwmc3ci()); // 用药频次
                drugDto3.setSfid(follow.getId());// 随访id
                drugList.add(drugDto3);
            }
            tnbDto.setDrugList(drugList);
            result.setData(tnbDto);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return result;
    }
}
