package com.java.moudle.common.controller;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.common.message.ProcessStatus;
import com.java.until.StringUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpServletResponse response;

	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 
	 * <li>描述:结果集 默认状态为0</li>
	 * <li>方法名称:jsonResult</li>
	 * <li>参数:@param data
	 * <li>参数:@return</li>
	 * <li>返回类型:JsonResult</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	public JsonResult jsonResult(Object data) {
		JsonResult result = new JsonResult(data);
		return result;
	}

	public JsonResult jsonResult() {
		JsonResult result = new JsonResult(null);
		return result;
	}

	/**
	 * 
	 * <li>描述:结果集加状态</li>
	 * <li>方法名称:jsonResult</li>
	 * <li>参数:@param data
	 * <li>参数:@param status
	 * <li>参数:@return</li>
	 * <li>返回类型:JsonResult</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	public JsonResult jsonResult(Object data, ProcessStatus status) {
		JsonResult result = new JsonResult(data, status);
		return result;
	}

	/**
	 * 
	 * <li>描述:</li>
	 * <li>方法名称:jsonResult</li>
	 * <li>参数:@param data 结果
	 * <li>参数:@param propertyKey 配置文件中的key
	 * <li>参数:@return</li>
	 * <li>返回类型:JsonResult</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	public JsonResult jsonResult(Object data, String propertyKey) {
		ProcessStatus status = new ProcessStatus();
		JsonResult result = new JsonResult(data, status);
		return result;
	}

	/**
	 * 
	 * <li>描述:</li>
	 * <li>方法名称:jsonResult</li>
	 * <li>参数:@param data
	 * <li>参数:@param retCode 状态码
	 * <li>参数:@param retMsg 描述
	 * <li>参数:@return</li>
	 * <li>返回类型:JsonResult</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	public JsonResult jsonResult(Object data, int retCode, String retMsg) {
		ProcessStatus status = new ProcessStatus(retCode, retMsg);
		JsonResult result = new JsonResult(data, status);
		return result;
	}


	public String getParam(HttpServletRequest request) {
		String paramStr = "";
		try {
			BufferedReader reader = null;
			request.setCharacterEncoding("UTF-8");
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String line = null;
			StringBuilder buffer = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			paramStr = new String(buffer);
			if (StringUntil.isNull(paramStr)) {
				paramStr = "{}";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramStr;
	}
}
