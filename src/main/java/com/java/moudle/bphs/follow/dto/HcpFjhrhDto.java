package com.java.moudle.bphs.follow.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HcpFjhrhDto {
    private String id;

    /** 身份证号 */
    private String sfzh;

    /** 患者编号 */
    private String hzbh;

    /** 随访方式 */
    private String sffs;

    /** 患者类型 */
    private String hzlx;

    /** 病菌情况 */
    private String bjqk;

    /** 耐药情况 */
    private String nyqk;

    private String zzyw;//症状有无

    /** 症状及体征 */
    private String zzjtz;

    /** 其他症状 */
    private String qtzz;

    /** 化疗方案 */
    private String hlfa;

    /** 用法 */
    private String yf;

    /** 药品剂型 */
    private String ypjx;

    /** 督导人员选择 */
    private String ddry;

    /** 单独的居室 */
    private String ddjs;

    /** 通风情况 */
    private String tfqk;

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

    /** 评估医生 */
    private String ys;

    /** 随访时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date sfsj;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date createTime;

    /** 创建机构 */
    private String orgId;

    /** 取药地点 */
    private String qydd;

    /** 取药时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date qysj;

    /** 服药记录卡的填写  */
    private String fyjlk;

    /** 服药方法及药品存放  */
    private String fyyfjcf;

    /** 肺结核治疗疗程   */
    private String zllc;

    /** 不规律服药危害  */
    private String fywh;

    /** 服药后不良反应及处理   */
    private String blfyjcl;

    /** 治疗期间复诊查痰   */
    private String fzct;

    /** 外出期间如何坚持服药   */
    private String jcfy;

    /** 生活习惯及注意事项  */
    private String sfxg;

    /** 密切接触者检查 */
    private String jczjc;

    /** 修改机构 */
    private String updateOrgId;

    /** 修改人 */
    private String updateUserId;

    /** 修改时间 */
    @JSONField(format="yyyy-MM-dd")
    private Date updateTime;

    /** 修改标志 */
    private String updateFlg;

    /** 是否删除 */
    private String status;

    /** 居民id */
    private String jmid;

    /** 督导人员其他描述 */
    private String ddryqt;

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

    public String getSffs() {
        return sffs;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs;
    }

    public String getHzlx() {
        return hzlx;
    }

    public void setHzlx(String hzlx) {
        this.hzlx = hzlx;
    }

    public String getBjqk() {
        return bjqk;
    }

    public void setBjqk(String bjqk) {
        this.bjqk = bjqk;
    }

    public String getNyqk() {
        return nyqk;
    }

    public void setNyqk(String nyqk) {
        this.nyqk = nyqk;
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

    public String getQtzz() {
        return qtzz;
    }

    public void setQtzz(String qtzz) {
        this.qtzz = qtzz;
    }

    public String getHlfa() {
        return hlfa;
    }

    public void setHlfa(String hlfa) {
        this.hlfa = hlfa;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getYpjx() {
        return ypjx;
    }

    public void setYpjx(String ypjx) {
        this.ypjx = ypjx;
    }

    public String getDdry() {
        return ddry;
    }

    public void setDdry(String ddry) {
        this.ddry = ddry;
    }

    public String getDdjs() {
        return ddjs;
    }

    public void setDdjs(String ddjs) {
        this.ddjs = ddjs;
    }

    public String getTfqk() {
        return tfqk;
    }

    public void setTfqk(String tfqk) {
        this.tfqk = tfqk;
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

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public Date getSfsj() {
        return sfsj;
    }

    public void setSfsj(Date sfsj) {
        this.sfsj = sfsj;
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

    public String getQydd() {
        return qydd;
    }

    public void setQydd(String qydd) {
        this.qydd = qydd;
    }

    public Date getQysj() {
        return qysj;
    }

    public void setQysj(Date qysj) {
        this.qysj = qysj;
    }

    public String getFyjlk() {
        return fyjlk;
    }

    public void setFyjlk(String fyjlk) {
        this.fyjlk = fyjlk;
    }

    public String getFyyfjcf() {
        return fyyfjcf;
    }

    public void setFyyfjcf(String fyyfjcf) {
        this.fyyfjcf = fyyfjcf;
    }

    public String getZllc() {
        return zllc;
    }

    public void setZllc(String zllc) {
        this.zllc = zllc;
    }

    public String getFywh() {
        return fywh;
    }

    public void setFywh(String fywh) {
        this.fywh = fywh;
    }

    public String getBlfyjcl() {
        return blfyjcl;
    }

    public void setBlfyjcl(String blfyjcl) {
        this.blfyjcl = blfyjcl;
    }

    public String getFzct() {
        return fzct;
    }

    public void setFzct(String fzct) {
        this.fzct = fzct;
    }

    public String getJcfy() {
        return jcfy;
    }

    public void setJcfy(String jcfy) {
        this.jcfy = jcfy;
    }

    public String getSfxg() {
        return sfxg;
    }

    public void setSfxg(String sfxg) {
        this.sfxg = sfxg;
    }

    public String getJczjc() {
        return jczjc;
    }

    public void setJczjc(String jczjc) {
        this.jczjc = jczjc;
    }

    public String getUpdateOrgId() {
        return updateOrgId;
    }

    public void setUpdateOrgId(String updateOrgId) {
        this.updateOrgId = updateOrgId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getDdryqt() {
        return ddryqt;
    }

    public void setDdryqt(String ddryqt) {
        this.ddryqt = ddryqt;
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
