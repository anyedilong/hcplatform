package com.java.moudle.tripartdock.region.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;


public class MedicalrecordDto implements Serializable {

	private static final long serialVersionUID = 1234234345345L;
	
	private String id;// 内码
	private String orgCode;// 医院组织机构代码
	private String orgName;// 医院名称
	private String depCode;// 科室代码
	private String depName;// 科室名称
	private String caseNum;// 病历号
	private String outpatientNum;// 本次门诊标识号
	private String sfzh;// 身份证号
	private String name;// 姓名
	private String sex;// 性别
	private String age;// 年龄
	@JSONField(format="yyyy-MM-dd")
	private Date visitingTime;// 就诊时间
	private String chiefCompliant;// 主诉
	private String presentIllness;// 现病史
	private String pastHistory;// 既往史
	private String physicalExam;// 体格检查
	private String treatIdea;// 处理意见
	private String doctorName;// 医生名称
	private String doctorCode;// 医生代码
	private String visitingType;// 就诊类别
	private String ageMonth;// 年龄(月)
	private String ageDay;// 年龄(天)
	private String ageHour;// 年龄(小时)

    private List<OutpatientZdDto> outpatientZdList;//门诊诊断
    private List<OutpatientImageDto> outpatientImageList;//门诊影像
    private List<OutpatientJcDto> outpatientJcList;//检测
    private List<OutpatientRecipeDto> outpatientRecipeList;//门诊处方

    private String visitingTypeText;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getOutpatientNum() {
        return outpatientNum;
    }

    public void setOutpatientNum(String outpatientNum) {
        this.outpatientNum = outpatientNum;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public String getChiefCompliant() {
        return chiefCompliant;
    }

    public void setChiefCompliant(String chiefCompliant) {
        this.chiefCompliant = chiefCompliant;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    public String getTreatIdea() {
        return treatIdea;
    }

    public void setTreatIdea(String treatIdea) {
        this.treatIdea = treatIdea;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getVisitingType() {
        return visitingType;
    }

    public void setVisitingType(String visitingType) {
        this.visitingType = visitingType;
    }

    public String getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(String ageMonth) {
        this.ageMonth = ageMonth;
    }

    public String getAgeDay() {
        return ageDay;
    }

    public void setAgeDay(String ageDay) {
        this.ageDay = ageDay;
    }

    public String getAgeHour() {
        return ageHour;
    }

    public void setAgeHour(String ageHour) {
        this.ageHour = ageHour;
    }

	public List<OutpatientZdDto> getOutpatientZdList() {
		return outpatientZdList;
	}

	public void setOutpatientZdList(List<OutpatientZdDto> outpatientZdList) {
		this.outpatientZdList = outpatientZdList;
	}

	public List<OutpatientImageDto> getOutpatientImageList() {
		return outpatientImageList;
	}

	public void setOutpatientImageList(List<OutpatientImageDto> outpatientImageList) {
		this.outpatientImageList = outpatientImageList;
	}

	public List<OutpatientJcDto> getOutpatientJcList() {
		return outpatientJcList;
	}

	public void setOutpatientJcList(List<OutpatientJcDto> outpatientJcList) {
		this.outpatientJcList = outpatientJcList;
	}

	public List<OutpatientRecipeDto> getOutpatientRecipeList() {
		return outpatientRecipeList;
	}

	public void setOutpatientRecipeList(List<OutpatientRecipeDto> outpatientRecipeList) {
		this.outpatientRecipeList = outpatientRecipeList;
	}

	public String getVisitingTypeText() {
		return visitingTypeText;
	}

	public void setVisitingTypeText(String visitingTypeText) {
		this.visitingTypeText = visitingTypeText;
	}
}
