package com.java.moudle.tripartdock.region.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.dto.HospitalImageDto;
import com.java.moudle.tripartdock.region.dto.HospitalInftoDto;
import com.java.moudle.tripartdock.region.dto.HospitalJcDto;
import com.java.moudle.tripartdock.region.dto.HospitalOrderDto;
import com.java.moudle.tripartdock.region.dto.HospitalOrderItemDto;
import com.java.moudle.tripartdock.region.dto.HospitalOutCostDto;
import com.java.moudle.tripartdock.region.dto.HospitalZdDto;
import com.java.moudle.tripartdock.region.service.RegionHospitalService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;
import com.java.until.http.HttpRequest;



@Named
public class RegionHospitalServiceImpl implements RegionHospitalService {

	private final String regionUrl = PropertiesUtil.getRegion("regionUrl");
	
	@Override
	public PageModel getHospitalPage(PageModel page, Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalPage"), paramMap);
		JSONObject result = JSON.parseObject(sendPost);
		
		int count = result.getInteger("count");
		List<HospitalInftoDto> list = new ArrayList<>();
		if(count > 0) {
			List<HospitalInftoDto> tempList = JSONObject.parseArray(result.getString("list"), HospitalInftoDto.class);
			if(tempList != null && tempList.size() > 0) {
				for(HospitalInftoDto info : tempList) {
					HospitalInftoDto temp = new HospitalInftoDto();
					temp.setId(info.getId());
					temp.setOrgName(info.getOrgName());
					temp.setAdmissionDate(info.getAdmissionDate());
					temp.setArId(info.getArId());
					temp.setOrgCode(info.getOrgCode());
					temp.setName(info.getName());
					temp.setDepCode(info.getDepCode());
					temp.setDepName(info.getDepName());
					list.add(temp);
				}
			}
			page.setCount(count);
			page.setList(list);
		}
		return page;
	}
	
	@Override
	public HospitalInftoDto getHospitalDetail(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalDetail"), paramMap);
		HospitalInftoDto info = JSON.parseObject(sendPost, HospitalInftoDto.class);
		return info;
	}

	@Override
	public List<HospitalOutCostDto> getHospitalCostList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalCostList"), paramMap);
		List<HospitalOutCostDto> list = JSON.parseArray(sendPost, HospitalOutCostDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public List<HospitalZdDto> getHospitalZDList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalZDList"), paramMap);
		List<HospitalZdDto> list = JSON.parseArray(sendPost, HospitalZdDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public String getHospitalBasy(Map<String, String> paramMap) throws Exception {
		String result = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalBasy"), paramMap);
		if (StringUntil.isNull(result))
            return "";
		return result;
	}
	
	@Override
	public List<HospitalImageDto> getHospitalImageList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalImageList"), paramMap);
		List<HospitalImageDto> list = JSON.parseArray(sendPost, HospitalImageDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public String getCoverImageFormat(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalCoverImageFormat"), paramMap);
		String parse = (String)JSON.parse(sendPost);
		return parse;
	}
	
	@Override
	public List<HospitalJcDto> getHospitalJcList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalJcList"), paramMap);
		List<HospitalJcDto> list = JSON.parseArray(sendPost, HospitalJcDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public HospitalInftoDto getAppHospitalDetail(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getAppHospitalDetail"), paramMap);
		HospitalInftoDto info = JSON.parseObject(sendPost, HospitalInftoDto.class);
		return info;
	}

	@Override
	public List<HospitalOrderDto> getHospitalOrderList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalOrderList"), paramMap);
		List<HospitalOrderDto> list = JSON.parseArray(sendPost, HospitalOrderDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public HospitalOrderDto getHospitalOrderDetail(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalOrderDetail"), paramMap);
		HospitalOrderDto info = JSON.parseObject(sendPost, HospitalOrderDto.class);
		return info;
	}

	@Override
	public List<HospitalOrderItemDto> getHospitalOrderItemList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getHospitalOrderItemList"), paramMap);
		List<HospitalOrderItemDto> list = JSON.parseArray(sendPost, HospitalOrderItemDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

}
