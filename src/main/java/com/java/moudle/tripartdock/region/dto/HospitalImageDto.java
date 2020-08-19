package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class HospitalImageDto implements Serializable {

	private static final long serialVersionUID = 156496496496L;
	
	private String id;// 内码
	private String arId;// 入院登记内码
	private String  imageUrl;//dcm文件路径
    private String  imageJpgUrl;//图片路径
	private String imageRemake;// B超描述
	private String imageJl;// B超结论
	@JSONField(format = "yyyy-MM-dd")
	private Date imageTime;// 检查时间
	private String imageType;
	private String hisId;

	public String getHisId() {
		return hisId;
	}

	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageRemake() {
		return imageRemake;
	}

	public void setImageRemake(String imageRemake) {
		this.imageRemake = imageRemake;
	}

	public String getImageJl() {
		return imageJl;
	}

	public void setImageJl(String imageJl) {
		this.imageJl = imageJl;
	}

	public Date getImageTime() {
		return imageTime;
	}

	public void setImageTime(Date imageTime) {
		this.imageTime = imageTime;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageJpgUrl() {
		return imageJpgUrl;
	}

	public void setImageJpgUrl(String imageJpgUrl) {
		this.imageJpgUrl = imageJpgUrl;
	}
}
