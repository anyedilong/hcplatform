package com.java.moudle.bphs.archive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HmBsxxDto {

	/**
	 *病史信息表
	 */
	@JsonProperty(value="bsCode")
	private String bsCode="";//   疾病类型 
	private String bsmc="";//   疾病名称 
	private String qtjbmc="";//   其他疾病名称 
	private String qzsj="";//   确诊时间 
	private String jmid="";
		
	

	public String getJmid() {
		return jmid;
	}
	public void setJmid(String jmid) {
		this.jmid = jmid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String jb="0";
	private String jbm="";
	private String id="";
	

	public String getJbm() {
		return jbm;
	}
	public void setJbm(String jbm) {
		this.jbm = jbm;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public String getBsCoed() {
		return bsCode;
	}
	public void setBsCoed(String bsCode) {
		 if(bsCode.indexOf("2")>=0){
			 this.jb="1";
			 
			 
		 }
		 if(bsCode.indexOf("3")>=0){
			 this.jb="2";
		 }
		 if(bsCode.indexOf("4")>=0){
			 this.jb="3";
		 }
		this.bsCode = bsCode;
	}
	public String getBsmc() {
		return bsmc;
	}
	public void setBsmc(String bsmc) {
		if(bsmc.indexOf("2")>=0 && bsmc.indexOf("2")<1){
			this.jbm="高血压";
		}
		if(bsmc.indexOf("3")>0 && bsmc.indexOf("3")<1){
			this.jbm="糖尿病";
		}
		if(bsmc.indexOf("4")>=0){
			this.jbm="冠心病";
		}
		if(bsmc.indexOf("5")>=0){
			this.jbm="慢性阻塞性肺疾病";
		}
		if(bsmc.indexOf("6")>=0){
			this.jbm="恶性肿瘤";
		}
		if(bsmc.indexOf("7")>=0){
			this.jbm="脑卒中";
		}
		if(bsmc.indexOf("8")>=0){
			this.jbm="重性精神疾病";
		}
		if(bsmc.indexOf("9")>=0){
			this.jbm="结核病";
		}
        if(bsmc.indexOf("10")>=0){
			this.jbm="肝炎";
		}
		if(bsmc.indexOf("11")>=0){
			this.jbm="其他法定传染病";
		}
		if(bsmc.indexOf("12")>=0){
			this.jbm="职业病";
		}
		if(bsmc.indexOf("13")>=0){
			this.jbm="其他";
		}
		this.bsmc = bsmc;
	}
	public String getQtjbmc() {
		return qtjbmc;
	}
	public void setQtjbmc(String qtjbmc) {
		this.qtjbmc = qtjbmc;
	}
	public String getQzsj() {
		return qzsj;
	}
	public void setQzsj(String qzsj) {
		this.qzsj = qzsj;
	}


	

}
