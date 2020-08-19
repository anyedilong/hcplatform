package com.java.moudle.system.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.java.until.dba.BaseDomain;

/**
 * 保健指导表(sys_physical_healthcare)
 * 
 * @author ljf
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "sys_physical_healthcare")
public class SysPhysicalHealthcare extends BaseDomain implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6198506111640906715L;

    /** id */
    @Id
    private String id;

    /** 体质类型-关联字典表 */
    private String physicalType;

    /** 图片1（预留） */
    private String emotionImageOne;
    @Transient 
    private MultipartFile emotionImageOneFile;

    /** 图片2（预留） */
    private String emotionImageTwo;
    @Transient
    private MultipartFile emotionImageTwoFile;
    
    /** 图片3（预留） */
    private String emotionImageThree;
    @Transient
    private MultipartFile emotionImageThreeFile;

    /** 情志调摄 */
    private String emotionContent;

    /** 图片1（预留） */
    private String dietImageOne;
    @Transient
    private MultipartFile dietImageOneFile;

    /** 图片2（预留） */
    private String dietImageTwo;
    @Transient
    private MultipartFile dietImageTwoFile;
    
    /** 图片3（预留） */
    private String dietImageThree;
    @Transient
    private MultipartFile dietImageTwoThree;

    /** 饮食调养 */
    private String dietContent;

    /** 图片1（预留） */
    private String livingImageOne;
    @Transient
    private MultipartFile livingImageOneFile;

    /** 图片2（预留） */
    private String livingImageTwo;
    @Transient
    private MultipartFile livingImageTwoFile;
    
    /** 图片3（预留） */
    private String livingImageThree;
    @Transient
    private MultipartFile livingImageThreeFile;

    /** 起居调摄 */
    private String livingContent;

    /** 图片1（预留） */
    private String sportsImageOne;
    @Transient
    private MultipartFile sportsImageOneFile;

    /** 图片2（预留） */
    private String sportsImageTwo;
    @Transient
    private MultipartFile sportsImageTwoFile;
    
    /** 图片3（预留） */
    private String sportsImageThree;
    @Transient
    private MultipartFile sportsImageThreeFile;

    /** 运动保健 */
    private String sportsContent;

    /** 图片1 */
    private String acupointImageOne;
    @Transient
    private MultipartFile acupointImageOneFile;

    /** 图片2 */
    private String acupointImageTwo;
    @Transient
    private MultipartFile acupointImageTwoFile;
    
    /** 图片2 */
    private String acupointImageThree;
    @Transient
    private MultipartFile acupointImageThreeFile;

    /** 穴位保健 */
    private String acupointContent;

    /** 图片1（预留） */
    private String otherImageOne;
    @Transient
    private MultipartFile otherImageOneFile;

    /** 图片2（预留） */
    private String otherImageTwo;
    @Transient
    private MultipartFile otherImageTwoFile;
    
    /** 图片3（预留） */
    private String otherImageThree;
    @Transient
    private MultipartFile otherImageThreeFile;
    
    /** 其他指导 */
    private String otherContent;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhysicalType() {
		return physicalType;
	}

	public void setPhysicalType(String physicalType) {
		this.physicalType = physicalType;
	}

	public String getEmotionImageOne() {
		return emotionImageOne;
	}

	public void setEmotionImageOne(String emotionImageOne) {
		this.emotionImageOne = emotionImageOne;
	}

	public MultipartFile getEmotionImageOneFile() {
		return emotionImageOneFile;
	}

	public void setEmotionImageOneFile(MultipartFile emotionImageOneFile) {
		this.emotionImageOneFile = emotionImageOneFile;
	}

	public String getEmotionImageTwo() {
		return emotionImageTwo;
	}

	public void setEmotionImageTwo(String emotionImageTwo) {
		this.emotionImageTwo = emotionImageTwo;
	}

	public MultipartFile getEmotionImageTwoFile() {
		return emotionImageTwoFile;
	}

	public void setEmotionImageTwoFile(MultipartFile emotionImageTwoFile) {
		this.emotionImageTwoFile = emotionImageTwoFile;
	}

	public String getEmotionImageThree() {
		return emotionImageThree;
	}

	public void setEmotionImageThree(String emotionImageThree) {
		this.emotionImageThree = emotionImageThree;
	}

	public MultipartFile getEmotionImageThreeFile() {
		return emotionImageThreeFile;
	}

	public void setEmotionImageThreeFile(MultipartFile emotionImageThreeFile) {
		this.emotionImageThreeFile = emotionImageThreeFile;
	}

	public String getEmotionContent() {
		return emotionContent;
	}

	public void setEmotionContent(String emotionContent) {
		this.emotionContent = emotionContent;
	}

	public String getDietImageOne() {
		return dietImageOne;
	}

	public void setDietImageOne(String dietImageOne) {
		this.dietImageOne = dietImageOne;
	}

	public MultipartFile getDietImageOneFile() {
		return dietImageOneFile;
	}

	public void setDietImageOneFile(MultipartFile dietImageOneFile) {
		this.dietImageOneFile = dietImageOneFile;
	}

	public String getDietImageTwo() {
		return dietImageTwo;
	}

	public void setDietImageTwo(String dietImageTwo) {
		this.dietImageTwo = dietImageTwo;
	}

	public MultipartFile getDietImageTwoFile() {
		return dietImageTwoFile;
	}

	public void setDietImageTwoFile(MultipartFile dietImageTwoFile) {
		this.dietImageTwoFile = dietImageTwoFile;
	}

	public String getDietImageThree() {
		return dietImageThree;
	}

	public void setDietImageThree(String dietImageThree) {
		this.dietImageThree = dietImageThree;
	}

	public MultipartFile getDietImageTwoThree() {
		return dietImageTwoThree;
	}

	public void setDietImageTwoThree(MultipartFile dietImageTwoThree) {
		this.dietImageTwoThree = dietImageTwoThree;
	}

	public String getDietContent() {
		return dietContent;
	}

	public void setDietContent(String dietContent) {
		this.dietContent = dietContent;
	}

	public String getLivingImageOne() {
		return livingImageOne;
	}

	public void setLivingImageOne(String livingImageOne) {
		this.livingImageOne = livingImageOne;
	}

	public MultipartFile getLivingImageOneFile() {
		return livingImageOneFile;
	}

	public void setLivingImageOneFile(MultipartFile livingImageOneFile) {
		this.livingImageOneFile = livingImageOneFile;
	}

	public String getLivingImageTwo() {
		return livingImageTwo;
	}

	public void setLivingImageTwo(String livingImageTwo) {
		this.livingImageTwo = livingImageTwo;
	}

	public MultipartFile getLivingImageTwoFile() {
		return livingImageTwoFile;
	}

	public void setLivingImageTwoFile(MultipartFile livingImageTwoFile) {
		this.livingImageTwoFile = livingImageTwoFile;
	}

	public String getLivingImageThree() {
		return livingImageThree;
	}

	public void setLivingImageThree(String livingImageThree) {
		this.livingImageThree = livingImageThree;
	}

	public MultipartFile getLivingImageThreeFile() {
		return livingImageThreeFile;
	}

	public void setLivingImageThreeFile(MultipartFile livingImageThreeFile) {
		this.livingImageThreeFile = livingImageThreeFile;
	}

	public String getLivingContent() {
		return livingContent;
	}

	public void setLivingContent(String livingContent) {
		this.livingContent = livingContent;
	}

	public String getSportsImageOne() {
		return sportsImageOne;
	}

	public void setSportsImageOne(String sportsImageOne) {
		this.sportsImageOne = sportsImageOne;
	}

	public MultipartFile getSportsImageOneFile() {
		return sportsImageOneFile;
	}

	public void setSportsImageOneFile(MultipartFile sportsImageOneFile) {
		this.sportsImageOneFile = sportsImageOneFile;
	}

	public String getSportsImageTwo() {
		return sportsImageTwo;
	}

	public void setSportsImageTwo(String sportsImageTwo) {
		this.sportsImageTwo = sportsImageTwo;
	}

	public MultipartFile getSportsImageTwoFile() {
		return sportsImageTwoFile;
	}

	public void setSportsImageTwoFile(MultipartFile sportsImageTwoFile) {
		this.sportsImageTwoFile = sportsImageTwoFile;
	}

	public String getSportsImageThree() {
		return sportsImageThree;
	}

	public void setSportsImageThree(String sportsImageThree) {
		this.sportsImageThree = sportsImageThree;
	}

	public MultipartFile getSportsImageThreeFile() {
		return sportsImageThreeFile;
	}

	public void setSportsImageThreeFile(MultipartFile sportsImageThreeFile) {
		this.sportsImageThreeFile = sportsImageThreeFile;
	}

	public String getSportsContent() {
		return sportsContent;
	}

	public void setSportsContent(String sportsContent) {
		this.sportsContent = sportsContent;
	}

	public String getAcupointImageOne() {
		return acupointImageOne;
	}

	public void setAcupointImageOne(String acupointImageOne) {
		this.acupointImageOne = acupointImageOne;
	}

	public MultipartFile getAcupointImageOneFile() {
		return acupointImageOneFile;
	}

	public void setAcupointImageOneFile(MultipartFile acupointImageOneFile) {
		this.acupointImageOneFile = acupointImageOneFile;
	}

	public String getAcupointImageTwo() {
		return acupointImageTwo;
	}

	public void setAcupointImageTwo(String acupointImageTwo) {
		this.acupointImageTwo = acupointImageTwo;
	}

	public MultipartFile getAcupointImageTwoFile() {
		return acupointImageTwoFile;
	}

	public void setAcupointImageTwoFile(MultipartFile acupointImageTwoFile) {
		this.acupointImageTwoFile = acupointImageTwoFile;
	}

	public String getAcupointImageThree() {
		return acupointImageThree;
	}

	public void setAcupointImageThree(String acupointImageThree) {
		this.acupointImageThree = acupointImageThree;
	}

	public MultipartFile getAcupointImageThreeFile() {
		return acupointImageThreeFile;
	}

	public void setAcupointImageThreeFile(MultipartFile acupointImageThreeFile) {
		this.acupointImageThreeFile = acupointImageThreeFile;
	}

	public String getAcupointContent() {
		return acupointContent;
	}

	public void setAcupointContent(String acupointContent) {
		this.acupointContent = acupointContent;
	}

	public String getOtherImageOne() {
		return otherImageOne;
	}

	public void setOtherImageOne(String otherImageOne) {
		this.otherImageOne = otherImageOne;
	}

	public MultipartFile getOtherImageOneFile() {
		return otherImageOneFile;
	}

	public void setOtherImageOneFile(MultipartFile otherImageOneFile) {
		this.otherImageOneFile = otherImageOneFile;
	}

	public String getOtherImageTwo() {
		return otherImageTwo;
	}

	public void setOtherImageTwo(String otherImageTwo) {
		this.otherImageTwo = otherImageTwo;
	}

	public MultipartFile getOtherImageTwoFile() {
		return otherImageTwoFile;
	}

	public void setOtherImageTwoFile(MultipartFile otherImageTwoFile) {
		this.otherImageTwoFile = otherImageTwoFile;
	}

	public String getOtherImageThree() {
		return otherImageThree;
	}

	public void setOtherImageThree(String otherImageThree) {
		this.otherImageThree = otherImageThree;
	}

	public MultipartFile getOtherImageThreeFile() {
		return otherImageThreeFile;
	}

	public void setOtherImageThreeFile(MultipartFile otherImageThreeFile) {
		this.otherImageThreeFile = otherImageThreeFile;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}