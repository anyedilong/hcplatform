package com.java.moudle.tripartdock.region.check.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.utils.properties.PropertiesUtil;
import com.java.moudle.system.dto.InitDictDto;
import com.java.moudle.tripartdock.region.check.dto.CheckDto;
import com.java.moudle.tripartdock.region.check.dto.JkjcDto;
import com.java.moudle.tripartdock.region.check.dto.JkwtbDto;
import com.java.moudle.tripartdock.region.check.dto.JzbcsDto;
import com.java.moudle.tripartdock.region.check.dto.JzsDto;
import com.java.moudle.tripartdock.region.check.dto.PgzdjlbDto;
import com.java.moudle.tripartdock.region.check.dto.ShfsDto;
import com.java.moudle.tripartdock.region.check.dto.XzjcjlbDto;
import com.java.moudle.tripartdock.region.check.dto.YbzzbDto;
import com.java.moudle.tripartdock.region.check.dto.YjjlbDto;
import com.java.moudle.tripartdock.region.check.dto.ZqgnbDto;
import com.java.moudle.tripartdock.region.check.dto.ZysDto;
import com.java.moudle.tripartdock.region.check.service.RegionCheckService;
import com.java.until.DictUtil;
import com.java.until.StringUntil;
import com.java.until.dba.PageModel;
import com.java.until.http.HttpUtil;


@Named
public class RegionCheckServiceImpl implements RegionCheckService {

	private String HOST = PropertiesUtil.getRegion("regionUrl");
	


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getFirstFjhDetail(String sfzh, String jcrq) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		//存储最新体检数据的容器
		Map<String, Object> newly = new HashMap<>();
		//存储上一次体检数据的容器
		Map<String, Object> old = new HashMap<>();
		//记录人群类型
		String rqlx = "";
		//查询最近两条体检
		String url = HOST + PropertiesUtil.getRegion("getCompareCheckInfo");
		url = url+"?sfzh="+sfzh+"&jcrq="+jcrq+"&pageNo=1&pageSize=2";
		String resStr = HttpUtil.doPost(url, "");
		JSONObject result = JSONObject.parseObject(resStr);
        String data = result.get("data").toString();
        if(!StringUntil.isNull(data)) {
        	data = HttpUtil.lineToHump(data);
        	JSONObject resultData = JSON.parseObject(data);
        	if(resultData.containsKey("rqlx")) {
        		rqlx = resultData.getString("rqlx");
        	}
        	if(resultData.containsKey("newly")) {
        		Map<String, Object> temp = (Map<String, Object>)resultData.get("newly");
        		//获取体检的基本信息
        		resultMap.put("tjdw", temp.get("tjdw"));//体检单位
        		resultMap.put("jcrq", temp.get("jcrq"));//检查日期
        		resultMap.put("tjbh", temp.get("tjbh"));//体检编号
        		resultMap.put("ys", temp.get("ys"));//医生
        		resultMap.put("jkzd", temp.get("jkzd"));//健康指导
        		resultMap.put("wxkz", temp.get("wxkz"));//危险控制
        		resultMap.put("mbtz", temp.get("gb"));//目标体重
        		resultMap.put("jzym", temp.get("zdjy"));//建议接种疫苗
        		resultMap.put("qtms", temp.get("qt"));//其他描述
        		this.coverTjData(rqlx, temp, newly);
        	}
        	if(resultData.containsKey("old")) {
        		Map<String, Object> temp = (Map<String, Object>)resultData.get("old");
        		this.coverTjData(rqlx, temp, old);
        	}
        }
        //查询温馨提示(需求变动；废除)
//        List<String> tips = pcService.getTipsByRqlx(rqlx);
//        String tipStr = "";
//        if(tips != null && tips.size() > 0) {
//        	for(String tip : tips) {
//        		if(StringUntil.isNull(tipStr)) {
//        			tipStr = tip + "。";
//        		}else {
//        			tipStr += tip + "。";
//        		}
//        	}
//        }
//        tipStr = tipStr.replaceAll(" ", "");
		resultMap.put("newly", newly);
		resultMap.put("old", old);
	//	resultMap.put("tip", tipStr);
		//return HttpUtil.providePost(PropertiesUtil.getFollow("prefix") + PropertiesUtil.getFollow("getCheckInfoNew"), paramJson);
		return resultMap;
	}
	
	@Override
	public PageModel getCheckPage(com.java.until.dba.PageModel page, String jmId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("getCheckPage");
		url = url+"?jmId="+jmId+"&pageNo="+page.getPageNo()+"&pageSize="+page.getPageSize();
		String sendPost = HttpUtil.doPost(url, "");
		JSONObject result = JSON.parseObject(sendPost);
		int count = result.getInteger("count");
		List<CheckDto> list = new ArrayList<>();
		if(count > 0) {
			list = JSONObject.parseArray(result.getString("list"), CheckDto.class);
			page.setCount(count);
			page.setList(list);
		}
		return page;
	}

	@Override
	public CheckDto getCheckDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryCheckDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		CheckDto check = JSON.parseObject(sendPost, CheckDto.class);
		return check;
	}
	
	@Override
	public JkjcDto getJkjcDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryJkjcDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		JkjcDto jkjc = JSON.parseObject(sendPost, JkjcDto.class);
		return jkjc;
	}

	@Override
	public JkwtbDto getJkwtbDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryJkwtbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		JkwtbDto jkwtb = JSON.parseObject(sendPost, JkwtbDto.class);
		return jkwtb;
	}

	@Override
	public PgzdjlbDto getPgzdjlbDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryPgzdjlbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		PgzdjlbDto pgzdjlb = JSON.parseObject(sendPost, PgzdjlbDto.class);
		return pgzdjlb;
	}

	@Override
	public ShfsDto getShfsDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryShfsDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		ShfsDto shfs = JSON.parseObject(sendPost, ShfsDto.class);
		return shfs;
	}

	@Override
	public XzjcjlbDto getXzjcjlbDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryXzjcjlbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		XzjcjlbDto xzjcjlb = JSON.parseObject(sendPost, XzjcjlbDto.class);
		return xzjcjlb;
	}

	@Override
	public YbzzbDto getYbzzbDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryYbzzbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		YbzzbDto ybzzb = JSON.parseObject(sendPost, YbzzbDto.class);
		return ybzzb;
	}

	@Override
	public ZqgnbDto getZqgnbDetail(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryZqgnbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		ZqgnbDto zqgnb = JSON.parseObject(sendPost, ZqgnbDto.class);
		return zqgnb;
	}

	@Override
	public List<JzbcsDto> getJzbcsList(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryJzbcsDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		List<JzbcsDto> list = JSON.parseArray(sendPost, JzbcsDto.class);
		return list;
	}

	@Override
	public List<JzsDto> getJzsList(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryJzsDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		List<JzsDto> list = JSON.parseArray(sendPost, JzsDto.class);
		return list;
	}

	@Override
	public List<YjjlbDto> getYjjlbList(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryYjjlbDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		List<YjjlbDto> list = JSON.parseArray(sendPost, YjjlbDto.class);
		return list;
	}

	@Override
	public List<ZysDto> getZysList(String tjId) throws Exception {
		String url = HOST + PropertiesUtil.getRegion("queryZysDetail");
		url = url+"?tjId="+tjId;
		String sendPost = HttpUtil.doPost(url, "");
		List<ZysDto> list = JSON.parseArray(sendPost, ZysDto.class);
		return list;
	}

	/**
	 * @Description: 体检关键数据项封装
	 * @param @param rqlx
	 * @param @param temp
	 * @param @param resultMap
	 * @return void
	 * @throws
	 */
	private void coverTjData(String rqlx, Map<String, Object> temp, Map<String, Object> resultMap) {
		List<InitDictDto> list = DictUtil.getDict("tjgjsj");
		for(InitDictDto info : list) {
			resultMap.put(info.getCode(), temp.get(info.getCode()));
		}
		
		//通过人群类型获取选中的关键数据项(需求变动，废除)
		//List<BltTjBaseData> baseDatas = pcService.getSelectedTjData(rqlx);
		//通过选中的关键数据项；进行数据的填充
		//if(baseDatas != null && baseDatas.size() > 0) {
			//for(BltTjBaseData info : baseDatas) {
//				JktjValueDto jktjValue = new JktjValueDto();
//				jktjValue.setName(info.getName());
//				//本次体检
//				//通过实体类中属性判断和从中取相关的值
//				if(newly.containsKey(info.getAttribute())) {
//					jktjValue.setValue1(newly.get(info.getAttribute()));
//				}
//				//通过实体类中属性有可能是字典；转译后自定义为属性+“Name”
//				if(newly.containsKey(info.getAttribute()+"Name")) {
//					jktjValue.setValue1(newly.get(info.getAttribute()+"Name"));
//				}
//				//上一次次体检
//				//通过实体类中属性判断和从中取相关的值
//				if(old.containsKey(info.getAttribute())) {
//					jktjValue.setValue2(old.get(info.getAttribute()));
//				}
//				//通过实体类中属性有可能是字典；转译后自定义为属性+“Name”
//				if(old.containsKey(info.getAttribute()+"Name")) {
//					jktjValue.setValue2(old.get(info.getAttribute()+"Name"));
//				}
//				resultList.add(jktjValue);
			//}
		//}
		//return resultList;
	}
}
