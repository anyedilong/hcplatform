package com.java.moudle.tripartdock.region.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.tripartdock.region.dto.MedicalrecordDto;
import com.java.moudle.tripartdock.region.dto.OutpatientImageDto;
import com.java.moudle.tripartdock.region.dto.OutpatientJcDto;
import com.java.moudle.tripartdock.region.dto.OutpatientRecipeDto;
import com.java.moudle.tripartdock.region.dto.OutpatientZdDto;
import com.java.moudle.tripartdock.region.service.RegionOutpatientService;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;
import com.java.until.http.HttpRequest;



@Named
public class RegionOutpatientServiceImpl implements RegionOutpatientService {

	private final String regionUrl = PropertiesUtil.getRegion("regionUrl");
	
	@Override
	public PageModel getOutpatientPage(PageModel page, Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientPage"), paramMap);
		JSONObject result = JSON.parseObject(sendPost);
		
		int count = result.getInteger("count");
		List<MedicalrecordDto> list = new ArrayList<>();
		if(count > 0) {
			List<MedicalrecordDto> tempList = JSONObject.parseArray(result.getString("list"), MedicalrecordDto.class);
			if(tempList != null && tempList.size() > 0) {
				for(MedicalrecordDto info : tempList) {
					MedicalrecordDto temp = new MedicalrecordDto();
					temp.setId(info.getId());
					temp.setOrgName(info.getOrgName());
					temp.setVisitingTime(info.getVisitingTime());
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
	public MedicalrecordDto getOutpatientDetail(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientDetail"), paramMap);
		MedicalrecordDto info = JSON.parseObject(sendPost, MedicalrecordDto.class);
		info.setOutpatientImageList(null);
		info.setOutpatientJcList(null);
		info.setOutpatientRecipeList(null);
		info.setOutpatientZdList(null);
		return info;
	}

	@Override
	public List<OutpatientRecipeDto> getOutpatientCfList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientCfList"), paramMap);
		List<OutpatientRecipeDto> list = JSON.parseArray(sendPost, OutpatientRecipeDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public List<OutpatientZdDto> getOutpatientZDList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientZDList"), paramMap);
		List<OutpatientZdDto> list = JSON.parseArray(sendPost, OutpatientZdDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public List<OutpatientImageDto> getOutpatientImageList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientImageList"), paramMap);
		List<OutpatientImageDto> list = JSON.parseArray(sendPost, OutpatientImageDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
	
	@Override
	public String getCoverImageFormat(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getCoverImageFormat"), paramMap);
		String parse = (String)JSON.parse(sendPost);
		return parse;
	}

	@Override
	public List<OutpatientJcDto> getOutpatientJcList(Map<String, String> paramMap) throws Exception {
		String sendPost = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getOutpatientJcList"), paramMap);
		List<OutpatientJcDto> list = JSON.parseArray(sendPost, OutpatientJcDto.class);
		if(list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

	@Override
	public String getJyList(Map<String, String> paramMap) throws Exception {
		String result = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getJyList"), paramMap);
		if (StringUntil.isNull(result))
            return "";
		return result;
	}

	@Override
	public String getJyDetail(Map<String, String> paramMap) throws Exception {
		String result = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getJyDetail"), paramMap);
		if (StringUntil.isNull(result))
            return "";
		return result;
	}

	@Override
	public String getJcList(Map<String, String> paramMap) throws Exception {
		String result = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getJcList"), paramMap);
		if (StringUntil.isNull(result))
            return "";
		return result;
	}

	@Override
	public String getJcDetail(Map<String, String> paramMap) throws Exception {
		String result = HttpRequest.sendPost(regionUrl + PropertiesUtil.getRegion("getJcDetail"), paramMap);
		if (StringUntil.isNull(result))
            return "";
		return result;
	}

}
