package com.java.moudle.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.java.moudle.system.dto.SysCustormerDto;
import com.java.until.dba.PageModel;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.system.dao.repository.SysCustormerRepository;
import com.java.moudle.system.domain.SysCustormer;
import com.java.moudle.system.dto.LoginInfoDto;
import com.java.moudle.system.dto.SysCustomerDto;
import com.java.moudle.system.dto.UpdatePhoneDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;

/**
 * @author ZhangWei
 * @Date: 2020-03-04 09:51
 **/
@Named
public class SysCustormerDao extends BaseDao<SysCustormerRepository, SysCustormer> {
	
    public void updatePassword(String phone, String pwd, String newPassword) {

        repository.updatePassword(phone, pwd, newPassword);
    }
    
    public List<LoginInfoDto> getCutormerList(String sfzh) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.gx, c.name, c.sfzh, c.phone, a.custormer_url  ");
		sql.append(" from sys_custormer a ");
		sql.append(" join sys_custormer_lnr b on a.id = b.custormer_id ");
		sql.append(" join sys_lnr c on b.lnr_id = c.id ");
		sql.append(" where a.sfzh = :sfzh ");
		paramMap.put("sfzh", sfzh);
		return queryList(sql.toString(), paramMap, LoginInfoDto.class);
	}
    
    public SysCustormer queryInfoByCon(String id, String username) {
    	Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from sys_custormer a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(id)) {
			sql.append(" and a.id = :id ");
			paramMap.put("id", id);
		}
		if(!StringUntil.isNull(username)) {
			sql.append(" and a.username = :username ");
			paramMap.put("username", username);
		}
		return queryOne(sql.toString(), paramMap, SysCustormer.class);
    }

	public SysCustormer queryInfoByOpenId(String openId) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* ");
		sql.append(" from sys_custormer a ");
		sql.append(" where a.status = '0' ");
		if(!StringUntil.isNull(openId)) {
			sql.append(" and a.open_id = :openId ");
			paramMap.put("openId", openId);
		}
		return queryOne(sql.toString(), paramMap, SysCustormer.class);
	}

	public void updatePhone(UpdatePhoneDto updatePhoneDto) {
		repository.updatePhone(updatePhoneDto);
	}
	
	public SysCustormer getCUstormerInfoByPhone(String telephone) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*  ");
		sql.append(" from sys_custormer a ");
		sql.append(" where 1 = 1 ");
		if(!StringUntil.isNull(telephone)) {
			sql.append(" and a.phone = :telephone ");
			paramMap.put("telephone", telephone);
		}
		return queryOne(sql.toString(), paramMap, SysCustormer.class);
	}
	
	public SysCustomerDto getPersonData(String sfzh) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.sfzh,a.name,a.phone,a.custormer_url custormerUrl  ");
		sql.append(" from sys_custormer a ");
		sql.append(" where a.status = '0' ");
			sql.append(" and a.sfzh = :sfzh ");
			paramMap.put("sfzh", sfzh);
		return queryOne(sql.toString(), paramMap, SysCustomerDto.class);
	}

	@Transactional
	public void updatePersonData(String sfzh,String custormerUrl) {
		repository.updatePersonData(sfzh, custormerUrl);
	}
	
	public void userlist(PageModel pageModel, SysCustormerDto sysCustormerDto) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id, phone, name, IS_REAL_NAME, status from sys_custormer where 1=1 ");

		if (StringUntil.isNotBlank(sysCustormerDto.getPhone())) {
			sql.append(" and phone like CONCAT('%',CONCAT(:phone,'%')) ");
		}
		if (StringUntil.isNotBlank(sysCustormerDto.getStatus())) {
			sql.append(" and status = :status ");
		}
		if (sysCustormerDto.getStartCreationTime() != null) {
			sql.append(" and create_Time >= :StartCreationTime ");
		}
		if (sysCustormerDto.getEndCreationTime() != null) {
			sql.append(" and create_Time <= :EndCreationTime");
		}

		queryPageList(sql.toString(), sysCustormerDto, pageModel, SysCustormer.class);
	}

	public void userstatus(String status, String id) {
		repository.userstatus(status, id);
	}
	
	public void updateUserHeadImage(String id, String headImage) {
		repository.updateUserHeadImage(id, headImage);
	}
}
