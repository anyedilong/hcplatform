package com.java.moudle.system.dao;

import javax.inject.Named;

import com.java.moudle.system.dao.repository.SysLinksRepository;
import com.java.moudle.system.domain.SysLinks;
import com.java.moudle.system.dto.SysLinksDto;
import com.java.until.StringUntil;
import com.java.until.dba.BaseDao;
import com.java.until.dba.PageModel;


@Named
public class SysLinksDao extends BaseDao<SysLinksRepository, SysLinks> {

	public void getLinksPage(SysLinks links, PageModel page) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select u.* ");
		sql.append(" from sys_links u ");
		sql.append(" where u.status = '0' ");
		queryPageList(sql.toString(), links, page, SysLinks.class);
	}

	public void getLinklist(SysLinksDto dto, PageModel pageModel) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from SYS_LINKS where status = '0' ");
		if (StringUntil.isNotBlank(dto.getName())) {
			sql.append(" and NAME like CONCAT('%',CONCAT(:name,'%')) ");
		}
		if (dto.getStartCreationTime() != null) {
			sql.append(" and CREATE_TIME >= :startCreationTime ");
		}
		if (dto.getEndCreationTime() != null) {
			sql.append(" and CREATE_TIME <= :endCreationTime ");
		}

		queryPageList(sql.toString(), dto, pageModel, SysLinks.class);
	}

	public void updateStatus(String id, String status) {
		repository.updateStatus(id, status);
	}

	public SysLinks getSysLinksByName(String name) {

		return repository.getSysLinksByName(name);
	}
}
