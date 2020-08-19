package com.java.moudle.system.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-16 08:47
 **/
public class SaveWzszDto implements Serializable {
    private static final long serialVersionUID = 3L;

    private String id;
    private MultipartFile logo;
    private String orgName;
    private String platName;
    private String welcomeContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getWelcomeContent() {
        return welcomeContent;
    }

    public void setWelcomeContent(String welcomeContent) {
        this.welcomeContent = welcomeContent;
    }
}
