package com.java.moudle.bphs.archive.dto;

public class JwsDto {


	private String id;
	private String jbcode;// 既往史code
	private String depict;// 名称
	private String qzsj;// 确诊时间
	private String jmid; //居民ID（关联）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJbcode() {
//    	String jbcode = DictUntils.getDictValue(InstanceFactory.getInstance(CacheParentCode.class).getIllnessType(), this.jbcode);
        if (jbcode != null) {
        	return jbcode;
        } else {
        	return this.jbcode;
        }
	}

	public void setJbcode(String jbcode) {
		this.jbcode = jbcode;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}


	public String getQzsj() {
		return qzsj;
	}

	public void setQzsj(String qzsj) {
		this.qzsj = qzsj;
	}

	public String getJmid() {
		return jmid;
	}

	public void setJmid(String jmid) {
		this.jmid = jmid;
	}

}
