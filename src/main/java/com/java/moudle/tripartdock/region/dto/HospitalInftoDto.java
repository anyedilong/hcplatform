package com.java.moudle.tripartdock.region.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.alibaba.fastjson.annotation.JSONField;

public class HospitalInftoDto implements Serializable {

	private static final long serialVersionUID = 64151466541L;
	
    private String id;//内码
    private String orgCode;//医院组织机构代码
    private String orgName;//医院名称
    private String name;//姓名
    private String sfzh;//身份证号
    private String sex;//性别
    private String age;//年龄
    @JSONField(format="yyyy-MM-dd")
    private Date bornDate;//出生日期
    private String occupation;//职业
    private String birthplace;//户籍所在地
    private String workingzOrg;//工作单位
    @JSONField(format="yyyy-MM-dd")
    private Date admissionDate;//入院日期
    private String admissionType;//入院方式
    private String chargeType;//收费类别
    private String depCode;//科室代码
    private String depName;//科室名称
    private String ward;//病室(区)
    private String sickbedNum;//床号
    private String dmissionSituatio;//入院时情况
    private String ageMonth;//年龄(月)
    private String ageDay;//年龄(天)
    private String ageHour;//年龄(小时)
    @Column(name = "ar_id")
    private String arId;//入院登记码
    @JSONField(format="yyyy-MM-dd")
    private Date dischargeDate;//出院日期

    private List<HospitalImageDto> hospitalImageList;//住院检测影像
    private List<HospitalJcDto> hospitalJcList;//住院检测项目
    private List<HospitalZdDto> hospitalZdList;//住院诊断
    private List<HospitalOutCostDto> hospitalOutCostList;//住院花费
    
    private String admissionTypeText;
    private String chargeTypeText;
    
    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public List<HospitalImageDto> getHospitalImageList() {
		return hospitalImageList;
	}

	public void setHospitalImageList(List<HospitalImageDto> hospitalImageList) {
		this.hospitalImageList = hospitalImageList;
	}

	public List<HospitalJcDto> getHospitalJcList() {
		return hospitalJcList;
	}

	public void setHospitalJcList(List<HospitalJcDto> hospitalJcList) {
		this.hospitalJcList = hospitalJcList;
	}

	public List<HospitalZdDto> getHospitalZdList() {
		return hospitalZdList;
	}

	public void setHospitalZdList(List<HospitalZdDto> hospitalZdList) {
		this.hospitalZdList = hospitalZdList;
	}

	public List<HospitalOutCostDto> getHospitalOutCostList() {
		return hospitalOutCostList;
	}

	public void setHospitalOutCostList(List<HospitalOutCostDto> hospitalOutCostList) {
		this.hospitalOutCostList = hospitalOutCostList;
	}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
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

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getWorkingzOrg() {
        return workingzOrg;
    }

    public void setWorkingzOrg(String workingzOrg) {
        this.workingzOrg = workingzOrg;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getSickbedNum() {
        return sickbedNum;
    }

    public void setSickbedNum(String sickbedNum) {
        this.sickbedNum = sickbedNum;
    }

    public String getDmissionSituatio() {
        return dmissionSituatio;
    }

    public void setDmissionSituatio(String dmissionSituatio) {
        this.dmissionSituatio = dmissionSituatio;
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

	public String getAdmissionTypeText() {
		return admissionTypeText;
	}

	public void setAdmissionTypeText(String admissionTypeText) {
		this.admissionTypeText = admissionTypeText;
	}

	public String getChargeTypeText() {
		return chargeTypeText;
	}

	public void setChargeTypeText(String chargeTypeText) {
		this.chargeTypeText = chargeTypeText;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

}
