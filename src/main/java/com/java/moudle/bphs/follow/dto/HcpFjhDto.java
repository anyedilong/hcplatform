package com.java.moudle.bphs.follow.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HcpFjhDto {

    private String id;

    /** 身份证号 */
    private String sfzh;

    /** 患者编号 */
    private String hzbh;

    /** 随访时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date sfsj;

    /** 督导人员 */
    private String ddry;

    /** 随访方式 */
    private String sffs;

    /** 治疗月序 */
    private String zlyx;

    private String zzyw;//症状有无

    /** 症状及体征 */
    private String zzjtz;

    /** 其他督导人员 */
    private String qt;

    /** 吸烟量 */
    private String xyl;

    /** 下次随访吸烟量 */
    private String xypl;

    /** 饮酒量 */
    private String yjl;

    /** 下次随访饮酒量 */
    private String yjpl;

    /** 下次随访时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date xcsfsj;

    /** 随访医生 */
    private String sfys;

    /** 化疗方案 */
    private String hlfa;

    /** 用药用法 */
    private String yyyf;

    /** 药品剂型 */
    private String ypjx;

    /** 漏服药次数 */
    private String lfycs;

    /** 药物不良反应 */
    private String ywblfy;

    /** 药物不良反应描述 */
    private String ywblfyms;

    /** 并发症或合并症 */
    private String bfzhhbz;

    /** 并发症或合并症描述 */
    private String bfzhhbzms;

    /** 停止治疗时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date tzzlsj;

    /** 停止治疗原因 */
    private String tzzlyy;

    /** 应访视患者次数 */
    private String yfshzcs;

    /** 实际访视次数 */
    private String sjfscs;

    /** 应服药次数 */
    private String yfycs;

    /** 实际服药次数 */
    private String sjfycs;

    /** 服药率 */
    private String fyl;

    /** 评估医生 */
    private String pgys;

    /** 转诊科别 */
    private String zzkb;

    /** 转诊原因 */
    private String zzyy;

    /** 转诊两周内随访结果 */
    private String zzsfjg;

    /** 转诊处理意见 */
    private String zzclyj;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;

    /** 创建机构 */
    private String orgId;

    /** 修改标志 */
    private String updateFlg;

    /** 删除标志 */
    private String status;

    /** 居民id */
    private String jmid;

    /** 其他症状 */
    private String qtzz;

    /** 转诊机构 */
    private String zzorg;

    /** 备注 */
    private String bz;

    /** 医生签名 */
    private String ysqm;
    /** 居民签名 */
    private String jmqm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getHzbh() {
        return hzbh;
    }

    public void setHzbh(String hzbh) {
        this.hzbh = hzbh;
    }

    public Date getSfsj() {
        return sfsj;
    }

    public void setSfsj(Date sfsj) {
        this.sfsj = sfsj;
    }

    public String getDdry() {
        return ddry;
    }

    public void setDdry(String ddry) {
        this.ddry = ddry;
    }

    public String getSffs() {
        return sffs;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs;
    }

    public String getZlyx() {
        return zlyx;
    }

    public void setZlyx(String zlyx) {
        this.zlyx = zlyx;
    }

    public String getZzyw() {
        return zzyw;
    }

    public void setZzyw(String zzyw) {
        this.zzyw = zzyw;
    }

    public String getZzjtz() {
        return zzjtz;
    }

    public void setZzjtz(String zzjtz) {
        this.zzjtz = zzjtz;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getXyl() {
        return xyl;
    }

    public void setXyl(String xyl) {
        this.xyl = xyl;
    }

    public String getXypl() {
        return xypl;
    }

    public void setXypl(String xypl) {
        this.xypl = xypl;
    }

    public String getYjl() {
        return yjl;
    }

    public void setYjl(String yjl) {
        this.yjl = yjl;
    }

    public String getYjpl() {
        return yjpl;
    }

    public void setYjpl(String yjpl) {
        this.yjpl = yjpl;
    }

    public Date getXcsfsj() {
        return xcsfsj;
    }

    public void setXcsfsj(Date xcsfsj) {
        this.xcsfsj = xcsfsj;
    }

    public String getSfys() {
        return sfys;
    }

    public void setSfys(String sfys) {
        this.sfys = sfys;
    }

    public String getHlfa() {
        return hlfa;
    }

    public void setHlfa(String hlfa) {
        this.hlfa = hlfa;
    }

    public String getYyyf() {
        return yyyf;
    }

    public void setYyyf(String yyyf) {
        this.yyyf = yyyf;
    }

    public String getYpjx() {
        return ypjx;
    }

    public void setYpjx(String ypjx) {
        this.ypjx = ypjx;
    }

    public String getLfycs() {
        return lfycs;
    }

    public void setLfycs(String lfycs) {
        this.lfycs = lfycs;
    }

    public String getYwblfy() {
        return ywblfy;
    }

    public void setYwblfy(String ywblfy) {
        this.ywblfy = ywblfy;
    }

    public String getYwblfyms() {
        return ywblfyms;
    }

    public void setYwblfyms(String ywblfyms) {
        this.ywblfyms = ywblfyms;
    }

    public String getBfzhhbz() {
        return bfzhhbz;
    }

    public void setBfzhhbz(String bfzhhbz) {
        this.bfzhhbz = bfzhhbz;
    }

    public String getBfzhhbzms() {
        return bfzhhbzms;
    }

    public void setBfzhhbzms(String bfzhhbzms) {
        this.bfzhhbzms = bfzhhbzms;
    }

    public Date getTzzlsj() {
        return tzzlsj;
    }

    public void setTzzlsj(Date tzzlsj) {
        this.tzzlsj = tzzlsj;
    }

    public String getTzzlyy() {
        return tzzlyy;
    }

    public void setTzzlyy(String tzzlyy) {
        this.tzzlyy = tzzlyy;
    }

    public String getYfshzcs() {
        return yfshzcs;
    }

    public void setYfshzcs(String yfshzcs) {
        this.yfshzcs = yfshzcs;
    }

    public String getSjfscs() {
        return sjfscs;
    }

    public void setSjfscs(String sjfscs) {
        this.sjfscs = sjfscs;
    }

    public String getYfycs() {
        return yfycs;
    }

    public void setYfycs(String yfycs) {
        this.yfycs = yfycs;
    }

    public String getSjfycs() {
        return sjfycs;
    }

    public void setSjfycs(String sjfycs) {
        this.sjfycs = sjfycs;
    }

    public String getFyl() {
        return fyl;
    }

    public void setFyl(String fyl) {
        this.fyl = fyl;
    }

    public String getPgys() {
        return pgys;
    }

    public void setPgys(String pgys) {
        this.pgys = pgys;
    }

    public String getZzkb() {
        return zzkb;
    }

    public void setZzkb(String zzkb) {
        this.zzkb = zzkb;
    }

    public String getZzyy() {
        return zzyy;
    }

    public void setZzyy(String zzyy) {
        this.zzyy = zzyy;
    }

    public String getZzsfjg() {
        return zzsfjg;
    }

    public void setZzsfjg(String zzsfjg) {
        this.zzsfjg = zzsfjg;
    }

    public String getZzclyj() {
        return zzclyj;
    }

    public void setZzclyj(String zzclyj) {
        this.zzclyj = zzclyj;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJmid() {
        return jmid;
    }

    public void setJmid(String jmid) {
        this.jmid = jmid;
    }

    public String getQtzz() {
        return qtzz;
    }

    public void setQtzz(String qtzz) {
        this.qtzz = qtzz;
    }

    public String getZzorg() {
        return zzorg;
    }

    public void setZzorg(String zzorg) {
        this.zzorg = zzorg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getYsqm() {
        return ysqm;
    }

    public void setYsqm(String ysqm) {
        this.ysqm = ysqm;
    }

    public String getJmqm() {
        return jmqm;
    }

    public void setJmqm(String jmqm) {
        this.jmqm = jmqm;
    }
}
