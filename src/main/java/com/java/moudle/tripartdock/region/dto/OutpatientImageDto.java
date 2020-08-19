package com.java.moudle.tripartdock.region.dto;


import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OutpatientImageDto implements Serializable {

	private static final long serialVersionUID = 524234345345L;
	
    private String  id;//内码
    private String  outpatientNum;//本次门诊标识号
    private String  imageUrl;//dcm文件路径
    private String  imageJpgUrl;//图片路径
    private String  imageRemake;//描述
    private String  imageJl;//结论
    @JSONField(format="yyyy-MM-dd")
    private Date imageTime;//检查时间
    private String imageType;//影像类型
	private String medicalRecordId;// 门诊病历id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutpatientNum() {
        return outpatientNum;
    }

    public void setOutpatientNum(String outpatientNum) {
        this.outpatientNum = outpatientNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageJpgUrl() {
		return imageJpgUrl;
	}

	public void setImageJpgUrl(String imageJpgUrl) {
		this.imageJpgUrl = imageJpgUrl;
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

	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}
}
