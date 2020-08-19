package com.java.moudle.tripartdock.region.archive.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.archive.dto.BsxxDto;
import com.java.moudle.tripartdock.region.archive.dto.JbxxjlbDto;
import com.java.moudle.tripartdock.region.archive.dto.JzsxxbDto;
import com.java.moudle.tripartdock.region.archive.dto.ShhjbDto;
import com.java.moudle.tripartdock.region.archive.service.RegionArchiceService;
import com.java.until.http.HttpUtil;


@Named
public class RegionArchiveServiceImpl implements RegionArchiceService {

	private String HOST = PropertiesUtil.getRegion("regionUrl");
	
	@Override
	public JbxxjlbDto getArchiveDetailBySfzh(String sfzh) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("getArchiveDetailBySfzh");
		url = url+"?sfzh="+sfzh;
		String sendPost = HttpUtil.doPost(url, "");
		JbxxjlbDto archive = JSON.parseObject(sendPost, JbxxjlbDto.class);
		if(archive == null) return null;
		archive.setXb("1".equals(archive.getXb()) ? "男" : "女");
		//年龄
		if(archive.getCsrq() != null)
			archive.setNl(this.getAge(archive.getCsrq()));
		return archive;
	}
	

	@Override
	public List<BsxxDto> getBSxxDetail(String jmId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryBSxxDetail");
		url = url+"?jmId="+jmId;
		String sendPost = HttpUtil.doPost(url, "");
		List<BsxxDto> bsxxList = JSON.parseArray(sendPost, BsxxDto.class);
		return bsxxList;
	}

	@Override
	public JzsxxbDto getJZSxxDetail(String jmId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryJZSxxDetail");
		url = url+"?jmId="+jmId;
		String sendPost = HttpUtil.doPost(url, "");
		JzsxxbDto jzsxx = JSON.parseObject(sendPost, JzsxxbDto.class);
		return jzsxx;
	}

	@Override
	public ShhjbDto getSHfsDetail(String jmId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("querySHhjDetail");
		url = url+"?jmId="+jmId;
		String sendPost = HttpUtil.doPost(url, "");
		ShhjbDto shhj = JSON.parseObject(sendPost, ShhjbDto.class);
		return shhj;
	}
   
	@Override
	public String queryRqlxBySfzh(String sfzh) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryRqlxBySfzh");
		url = url+"?sfzh="+sfzh;
		String sendPost = HttpUtil.doPost(url, "");
		return sendPost;
	}
	
	/**
	 * 	计算年龄
	 */
	private String getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance(); 
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay); 
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
        int age = yearNow - yearBirth;   //计算整岁数
        int month = monthNow - monthBirth;
        int day = dayOfMonthNow - dayOfMonthBirth;
        if(age > 6) {
        	return age+"";
        }else if(age > 1 && age <=6) {
        	return age + "岁" + month + "个月";
        }else {
        	return month + "月" + day + "天";
        }
	}

}
