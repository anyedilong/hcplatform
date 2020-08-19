package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;


public class OutpatientRxZdDto implements Serializable {

	private static final long serialVersionUID = 126846455345L;
	
    private String id;//
    private String hisId;//院内id，内码
    private String orId;//门诊处方内码
    private String diagnosisType;//门诊处方疾病诊断类型
    private String diseaseCode;//平台门诊处方疾病诊断代码
    private String hisDiseaseCode;//院内his诊断代码
    private String diseaseName;//门诊处方疾病诊断名称
    private String sn;//序号，诊断顺序
    private String orgCode;

    private String diagnosisTypeText;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
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

    public String getHisDiseaseCode() {
        return hisDiseaseCode;
    }

    public void setHisDiseaseCode(String hisDiseaseCode) {
        this.hisDiseaseCode = hisDiseaseCode;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getDiagnosisTypeText() {
		return diagnosisTypeText;
	}


	public void setDiagnosisTypeText(String diagnosisTypeText) {
		this.diagnosisTypeText = diagnosisTypeText;
	}

}
