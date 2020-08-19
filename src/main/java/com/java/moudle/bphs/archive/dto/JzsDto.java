package com.java.moudle.bphs.archive.dto;


public class JzsDto {


	private String id;
	private String cycode;// 家族成员code,1父亲，2母亲，3兄弟，4子女
	private String jzscode;// 家族史code
	private String depict;// 描述
    private String jmid; //居民ID（关联字段）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCycode() {
		return cycode;
	}

	public void setCycode(String cycode) {
		this.cycode = cycode;
	}

	public String getJzscode() {
//    	String jzscode = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getIllnessType(), this.jzscode);
        if (jzscode != null) {
        	return jzscode;
        } else {
        	return this.jzscode;
        }
	}

	public void setJzscode(String jzscode) {
		this.jzscode = jzscode;
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
