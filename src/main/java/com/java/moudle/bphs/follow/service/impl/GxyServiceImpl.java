package com.java.moudle.bphs.follow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.java.moudle.bphs.follow.dto.HcpMbDrugDto;
import com.java.moudle.bphs.follow.dto.HcpMxGxyDto;
import com.java.moudle.bphs.follow.dto.Mxgxysfb;
import com.java.moudle.bphs.follow.service.GxyService;
import com.java.moudle.common.message.JsonResult;
import com.java.until.StringUtils;
import com.java.until.ToJavaUtils;
import com.java.until.http.HttpUtil;

@Named
public class GxyServiceImpl implements GxyService {
    /**
     * @Title : 将老公卫返回的高血压详情数据转换为新公卫格式一样的数据
     * @Description : 
     * @author : 皮雪平
     * @date : 2020/3/13 10:57 
     * @param : 
     * @return : 
     * @throws
     */
    @Override
    public JsonResult getGxyDetail(String url, Map<String, String> paramMap) {
        JsonResult result = HttpUtil.providePost(url, paramMap);
        String data = result.getData().toString();
        Mxgxysfb followGxy = JSON.parseObject(data, Mxgxysfb.class);
        HcpMxGxyDto hcpGxy = new HcpMxGxyDto();
        try {
            ToJavaUtils.copyFields(followGxy, hcpGxy);
            hcpGxy.setSfys(followGxy.getSfysmc());
            List<HcpMbDrugDto> drugList = new ArrayList<>();
            List<HcpMbDrugDto> tzdrugList = new ArrayList<>();
            HcpMbDrugDto drugDto = new HcpMbDrugDto();

            //目前用药
            if (!StringUtils.isNull(followGxy.getYwmc1())) {
                HcpMbDrugDto drugDto1 = (HcpMbDrugDto) drugDto.clone();
                drugDto1.setYwmc(followGxy.getYwmc1()); // 用药名称
                drugDto1.setYwjl(followGxy.getYwmc1mg());//剂量
                drugDto1.setYwdw(followGxy.getYwmc1dw()); // 单次单位
                drugDto1.setYwpc(followGxy.getYwmc1pc()); // 用药频次
                drugDto1.setYwyf(followGxy.getYwmc1yf()); // 用法
                drugDto1.setSfid(followGxy.getId());// 随访id
                drugList.add(drugDto1);
            }
            if (!StringUtils.isNull(followGxy.getYwmc2())) {
                HcpMbDrugDto drugDto2 = (HcpMbDrugDto) drugDto.clone();
                drugDto2.setYwmc(followGxy.getYwmc2()); // 用药名称
                drugDto2.setYwjl(followGxy.getYwmc2mg());//剂量
                drugDto2.setYwdw(followGxy.getYwmc2dw()); // 单次单位
                drugDto2.setYwpc(followGxy.getYwmc2pc()); // 用药频次
                drugDto2.setYwyf(followGxy.getYwmc2yf()); // 用法
                drugDto2.setSfid(followGxy.getId());// 随访id
                drugList.add(drugDto2);
            }
            if (!StringUtils.isNull(followGxy.getYwmc3())) {
                HcpMbDrugDto drugDto3 = (HcpMbDrugDto) drugDto.clone();
                drugDto3.setYwmc(followGxy.getYwmc3()); // 用药名称
                drugDto3.setYwjl(followGxy.getYwmc3mg());//剂量
                drugDto3.setYwdw(followGxy.getYwmc3dw()); // 单次单位
                drugDto3.setYwpc(followGxy.getYwmc3pc()); // 用药频次
                drugDto3.setYwyf(followGxy.getYwmc3yf()); // 用法
                drugDto3.setSfid(followGxy.getId());// 随访id
                drugList.add(drugDto3);
            }
            if (!StringUtils.isNull(followGxy.getYwmc4())) {
                HcpMbDrugDto drugDto4 = (HcpMbDrugDto) drugDto.clone();
                drugDto4.setYwmc(followGxy.getYwmc4()); // 用药名称
                drugDto4.setYwjl(followGxy.getYwmc4mg());//剂量
                drugDto4.setYwdw(followGxy.getYwmc4dw()); // 单次单位
                drugDto4.setYwpc(followGxy.getYwmc4pc()); // 用药频次
                drugDto4.setYwyf(followGxy.getYwmc4yf()); // 用法
                drugDto4.setSfid(followGxy.getId());// 随访id
                drugList.add(drugDto4);
            }
            if (!StringUtils.isNull(followGxy.getYwmc5())) {
                HcpMbDrugDto drugDto5 = (HcpMbDrugDto) drugDto.clone();
                drugDto5.setYwmc(followGxy.getYwmc5()); // 用药名称
                drugDto5.setYwjl(followGxy.getYwmc5mg());//剂量
                drugDto5.setYwdw(followGxy.getYwmc5dw()); // 单次单位
                drugDto5.setYwpc(followGxy.getYwmc5pc()); // 用药频次
                drugDto5.setYwyf(followGxy.getYwmc5yf()); // 用法
                drugDto5.setSfid(followGxy.getId());// 随访id
                drugList.add(drugDto5);
            }

            //调整用药
            if (!StringUtils.isNull(followGxy.getQtyw())) {
                HcpMbDrugDto tzyyDto1 = (HcpMbDrugDto) drugDto.clone();
                tzyyDto1.setYwmc(followGxy.getQtyw()); // 用药名称
                tzyyDto1.setYwjl(followGxy.getQtywmg());// 单次剂量
                tzyyDto1.setYwdw(followGxy.getQtywdw()); // 单次单位
                tzyyDto1.setYwpc(followGxy.getQtywpc()); // 用药频次
                tzyyDto1.setYwyf(followGxy.getQtywyf()); // 用法
                tzyyDto1.setSfid(followGxy.getId()); // 随访id
                tzdrugList.add(tzyyDto1);
            }
            if (!StringUtils.isNull(followGxy.getQtyw2())) {
                HcpMbDrugDto tzyyDto2 = (HcpMbDrugDto) drugDto.clone();
                tzyyDto2.setYwmc(followGxy.getQtyw2()); // 用药名称
                tzyyDto2.setYwjl(followGxy.getQtyw2mg());// 单次剂量
                tzyyDto2.setYwdw(followGxy.getQtyw2dw()); // 单次单位
                tzyyDto2.setYwpc(followGxy.getQtyw2pc()); // 用药频次
                tzyyDto2.setYwyf(followGxy.getQtyw2yf()); // 用法
                tzyyDto2.setSfid(followGxy.getId()); // 随访id
                tzdrugList.add(tzyyDto2);
            }
            if (!StringUtils.isNull(followGxy.getQtyw3())) {
                HcpMbDrugDto tzyyDto3 = (HcpMbDrugDto) drugDto.clone();
                tzyyDto3.setYwmc(followGxy.getQtyw3()); // 用药名称
                tzyyDto3.setYwjl(followGxy.getQtyw3mg());// 单次剂量
                tzyyDto3.setYwdw(followGxy.getQtyw3dw()); // 单次单位
                tzyyDto3.setYwpc(followGxy.getQtyw3pc()); // 用药频次
                tzyyDto3.setYwyf(followGxy.getQtyw3yf()); // 用法
                tzyyDto3.setSfid(followGxy.getId()); // 随访id
                tzdrugList.add(tzyyDto3);
            }
            if (!StringUtils.isNull(followGxy.getQtyw4())) {
                HcpMbDrugDto tzyyDto4 = (HcpMbDrugDto) drugDto.clone();
                tzyyDto4.setYwmc(followGxy.getQtyw4()); // 用药名称
                tzyyDto4.setYwjl(followGxy.getQtyw4mg());// 单次剂量
                tzyyDto4.setYwdw(followGxy.getQtyw4dw()); // 单次单位
                tzyyDto4.setYwpc(followGxy.getQtyw4pc()); // 用药频次
                tzyyDto4.setYwyf(followGxy.getQtyw4yf()); // 用法
                tzyyDto4.setSfid(followGxy.getId()); // 随访id
                tzdrugList.add(tzyyDto4);
            }
            if (!StringUtils.isNull(followGxy.getQtyw5())) {
                HcpMbDrugDto tzyyDto5 = (HcpMbDrugDto) drugDto.clone();
                tzyyDto5.setYwmc(followGxy.getQtyw5()); // 用药名称
                tzyyDto5.setYwjl(followGxy.getQtyw5mg());// 单次剂量
                tzyyDto5.setYwdw(followGxy.getQtyw5dw()); // 单次单位
                tzyyDto5.setYwpc(followGxy.getQtyw5pc()); // 用药频次
                tzyyDto5.setYwyf(followGxy.getQtyw5yf()); // 用法
                tzyyDto5.setSfid(followGxy.getId()); // 随访id
                tzdrugList.add(tzyyDto5);
            }
            hcpGxy.setDrugList(drugList);
            hcpGxy.setTzdrugList(tzdrugList);
            result.setData(hcpGxy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
