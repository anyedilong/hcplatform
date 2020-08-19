package com.java.moudle.bphs.archive.dto;


public class ZffsDto {


	private String id;
	private String code;// 支付方式代码
	private String depict;// 名称
	private String jmid; //居民ID（关联）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {    	
//		String code = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getRHBloodType(), this.code);
	    if (code != null) {
	    	return code;
	    } else {
	    	return this.code;
	    }
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}

	public String getJmid() {
		return jmid;
	}

	public void setJmid(String jmid) {
		this.jmid = jmid;
	}

}
