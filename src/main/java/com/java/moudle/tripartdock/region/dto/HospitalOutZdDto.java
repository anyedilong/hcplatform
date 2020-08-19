package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class HospitalOutZdDto implements Serializable {

	private static final long serialVersionUID = 3546596496L;
	
    private  String id;
    private  String isId;
    private  String diagnosisType;
    private  String diseaseCode;
    private  String diseaseName;
    @JSONField(format="yyyy-MM-dd")
    private  Date diagnosisTime;
    private  String hisId;
    
    private String diagnosisTypeText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsId() {
        return isId;
    }

    public void setIsId(String isId) {
        this.isId = isId;
    }

    public String getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(String diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Date getDiagnosisTime() {
        return diagnosisTime;
    }

    public void setDiagnosisTime(Date diagnosisTime) {
        this.diagnosisTime = diagnosisTime;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

	public String getDiagnosisTypeText() {
		return diagnosisTypeText;
	}

	public void setDiagnosisTypeText(String diagnosisTypeText) {
		this.diagnosisTypeText = diagnosisTypeText;
	}
}
