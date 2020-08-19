package com.java.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.moudle.system.service.VisitStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysCustormerService;
import com.java.moudle.system.service.SysRoleService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.SysUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;

@Configuration
public class WebFilterInterceptor extends WebMvcConfigurationSupport {

    @Autowired
    private VisitStatService visitStatService;
    @Value("${encryStr}")
    private String encryStr;

	private static List<String> userList = new ArrayList<>();
	private static List<String> customerList = new ArrayList<>();
	
	static {
		// 测试 jktj
		customerList.add("/jktj/**");//网站端登录
		
		//后台管理
		userList.add("/login/**");//后台管理登录

		//网站端
		customerList.add("/main/**");//网站端登录
		customerList.add("/identCodelogin/**");//网站端验证码登录
		userList.add("/wxlogin/**");//网站端微信登录
		customerList.add("/system/front/**"); //获取系统消息
		customerList.add("/guidecat/getGuideCategoryList");//获取办事指南类别
		customerList.add("/guide/getGuidePage");//获取办事指南类别
		customerList.add("/guide/show");//获取办事指南详情
		customerList.add("/sys/policy/**");//获取政策法规
		customerList.add("/lb/**");//获取健康资讯
		customerList.add("/sys/links/getLinksPage");//获取友情链接
		customerList.add("/sys/aboutus/queryInfo");//获取关于我们
		customerList.add("/sys/callus/queryInfo");//获取联系我们
		customerList.add("/sys/opinion/save");//获取资讯投诉
		customerList.add("/web/xq");//获取平台信息
		customerList.add("/other/search");//热门搜索
		customerList.add("/sys/customer/updatePassword");//修改密码
		customerList.add("/commontools/getIdentCode");//获取手机验证码
		customerList.add("/commontools/checkCode");//获取手机验证码
		customerList.add("/sys/customer/addLxr");//新增联系人
		customerList.add("/sys/customer/getLxrList");//获取联系人列表
		customerList.add("/sys/customer/delLxr");//删除联系人
		customerList.add("/lnr/**");//老年人
		customerList.add("/web/save");//网站管理
		customerList.add("/jzs/**");//已接种信息
		customerList.add("/follow/**");//随访列表
		customerList.add("/ncz/**");//脑卒中随访
		customerList.add("/cjr/**");//残疾人随访
		customerList.add("/et/**");//儿童随访
		customerList.add("/ycf/**");//孕产妇随访
		customerList.add("/ncz/**");//脑卒中随访
		customerList.add("/tjgjsj/**");
		customerList.add("/sys/customer/getBankTypeList");
		customerList.add("/sys/customer/createUser");
		customerList.add("/sys/customer/realNameTest");
		customerList.add("/sys/customer/realNameConfirm");
		customerList.add("/sys/customer/checkAccount");
		customerList.add("/help/getCommonProblem"); // 使用帮助
		customerList.add("/wxlogin/weChatLogin");//微信登陆获取token
        customerList.add("/guidance/getPhysicalByType");
        customerList.add("/sys/appversion/*");//手机app更新版本
		customerList.add("/index.html**");
		customerList.add("/MP_verify_kjU5B6Z17C9OMURE.txt**");
		//第三方平台
		customerList.add("/treat/turn/**");
		customerList.add("/region/hospital/**");
		customerList.add("/region/outpatient/**");
		customerList.add("/healthdoctor/appoint/**");

	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**");
	}
    
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}
    
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //升级最新版本需加=============================================================
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        //WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        //WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        //DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        //WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        //WriteMapNullValue：是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

		//统计访问量
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String path = request.getServletPath();
                if("/system/front/getNewsPage".equals(path)) {
                	visitStatService.add("0", "", request.getRemoteAddr());
                }
                return true;
            }
        }).addPathPatterns(customerList);

    	/**
         * 	平台验证
         */
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            	String path = request.getServletPath();
            	String method = request.getMethod();
				if(method.equalsIgnoreCase("OPTIONS")){
					response.getOutputStream().write("Success".getBytes("utf-8"));
				}
				//从header里获取参数client+username+token
				Map<String, Object> headerMap = SysUtil.getSecurityKey(request);
				if(headerMap == null) {
					onAuthFail(response);
					return false;
				}
				//SysUtil.getSecurityKey(request);
				// 客户端请求的缓存key
				String securitykey = (String) headerMap.get("securitykey");
				// 验证缓存中无数据，需要重新获取缓存信息
				String jsonStr = CacheUntil.get(RedisCacheEmun.USER_CACHE, securitykey, String.class);
				if (null == jsonStr) {
					onAuthFail(response);
					return false;
				}
				//获取最新的登录人的access_token
				String newAccessToken = JSONObject.parseObject(jsonStr).getString("accessToken");
				//获取登录用户accessToken参数；与最新的进行比较
				String token = (String) headerMap.get("securitytoken");
				if(token == null || !token.equals(newAccessToken)) {
					onForcelOffline(response);
					return false;
				}
				String userType = (String) headerMap.get("userType");
				SysUser user = null;
				SysCustormer customer = null;
				if("1".equals(userType)) {
					//后台管理;用户缓存key:token
					user = CacheUntil.get(RedisCacheEmun.USER_CACHE, token, SysUser.class);
				}else if("2".equals(userType)){
					//惠民平台用户缓存key:项目名加手机号（方便后台更新用户信息时更新redis中的信息）
					customer = CacheUntil.get(RedisCacheEmun.USER_CACHE, securitykey+encryStr, SysCustormer.class);
				}
				if(user == null && customer == null) {
					//从缓存中获取token (key的值待定)
					String refreshToken = "refresh_auth:" + JSONObject.parseObject(jsonStr).getString("refreshToken");
					String authParamStr = CacheUntil.get(RedisCacheEmun.USER_CACHE, refreshToken, String.class);
					//通过用户id获取登录者信息
					String principalStr = JSONObject.parseObject(authParamStr).getString("principal");
					String userId = JSONObject.parseObject(principalStr).getString("id");

					if("1".equals(userType)) {
						//后台管理
						user = InstanceFactory.getInstance(SysUserService.class).get(userId);
						//得到用户的角色信息
						SysRole role = InstanceFactory.getInstance(SysRoleService.class).getRoleInfoByUserId(userId);
						if(role == null) {
							onAuthFail(response);
							return false;
						}
						user.setRole(role);
						CacheUntil.put(RedisCacheEmun.USER_CACHE, token, user);
					}else if("2".equals(userType)){
						//惠民平台
						customer = InstanceFactory.getInstance(SysCustormerService.class).get(userId);
						CacheUntil.put(RedisCacheEmun.USER_CACHE, securitykey+encryStr, customer);
					}
				}
				//判断网站端用户是否被禁用
				if(customer != null && "1".equals(customer.getStatus())) {
					CacheUntil.delete(RedisCacheEmun.USER_CACHE, securitykey+encryStr);
					onAuthFail(response);
					return false;
				}
				
				//统计访问量
				//String path = request.getServletPath();
                if("2".equals(userType)){
                    //惠民平台
                	if("/sys/customer/getLoginInfo".equals(path)) 
                		visitStatService.add(userType, customer == null ? "" : customer.getId(), request.getRemoteAddr());
                }
            	return true;
            }
        }).addPathPatterns("/**")
        .excludePathPatterns(userList).excludePathPatterns(customerList);
    }
    /**
	 * <li>描述:身份认证错误默认返回1003状态码</li>
	 * <li>方法名称:onAuthFail</li>
	 * <li>参数:@param response
	 * <li>参数:@throws Exception</li>
	 * <li>返回类型:void</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	private void onAuthFail(ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		response.setContentType("application/json;charset=UTF-8");
		//JsonResult ret = new JsonResult("身份认证错误", ProcessStatus.AUTH_ERROR);
		//ret.setResponseStatus(ResponseStatus.HTTP_UNAUTHORIZED);
		httpResponse.getWriter().write("{\"retCode\":1003,\"retMsg\":\"身份认证错误\"}");
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
	/**
	 * <li>描述:强制下线默认返回1006状态码</li>
	 * <li>方法名称:onAuthFail</li>
	 * <li>参数:@param response
	 * <li>参数:@throws Exception</li>
	 * <li>返回类型:void</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	private void onForcelOffline(ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		response.setContentType("application/json;charset=UTF-8");
		//JsonResult ret = new JsonResult("身份认证错误", ProcessStatus.AUTH_ERROR);
		//ret.setResponseStatus(ResponseStatus.HTTP_UNAUTHORIZED);
		httpResponse.getWriter().write("{\"retCode\":1006,\"retMsg\":\"您的账号在其他地方登陆,您已经被强制下线\"}");
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
