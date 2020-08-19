package com.java.moudle.bphs.follow.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

public class HcpMxGxyDto {

    private String id;

    /** 体检编号  */
    private String tjbh;

    /** 档案号  */
    private String dah;

    /** 患者编号  */
    private String hzbh;

    /** 身份证号  */
    private String sfzh;

    /** 患者姓名  */
    private String hzxm;

    /** 性别 */
    private String xb;

    /** 剩余天数或者超时天数 */
    private String days;

    /** 随访日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date sfrq;

    /** 随访医生  */
    private String sfys;

    /** 下次随访日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date xcsfrq;

    private String zzyw;//有无症状

    /** 症状  */
    private String zz;

    /** 其他症状  */
    private String qtzz;

    /** 高血压  */
    private String gxy;

    /** 低血压  */
    private String dxy;

    /** 体重  */
    private String tz;

    /** 体质指数 */
    private String tzzs;

    /** 心率  */
    private String xl;

    /** 其他 */
    private String qt;

    /** 日吸烟量  */
    private String rxyl;

    /** 日饮酒量  */
    private String ryjl;

    /** 每周运动次数  */
    private String mzydcs;

    /** 每次运动时间  */
    private String mzydsj;

    /** 摄盐情况  */
    private String syqk;

    /** 下次随访摄盐量  */
    private String xcsfsyl;

    /** 心理调整  */
    private String xltz;

    /** 遵医行为  */
    private String zyxw;

    /** 辅助检查  */
    private String fzjc;

    /** 服药依从性  */
    private String fyycx;

    /** 药物不良反应  */
    private String ywblfy;

    /** 药物不良反应描述  */
    private String blfyms;

    /** 此次随访分类  */
    private String sffl;

    /** 转诊原因  */
    private String zzyy;

    /** 随访方式  */
    private String sffs;

    /** 下次随访体重  */
    private String xcsftz;

    /** 下次随访体质指数  */
    private String xcsftzzs;

    /** 下次随访日吸烟量  */
    private String xcsfrxyl;

    /** 下次随访日饮酒量  */
    private String xcsfryjl;

    /** 下次随访每周运动次数  */
    private String xcsfmzydcs;

    /** 下次随访每次运动时间  */
    private String xcsfmcydsj;

    /** 建表人  */
    private String jbr;

    /** 建表日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date jbrq;

    /** 上次修改人  */
    private String scxgr;

    /** 上次修改时间  */
    @JSONField(format="yyyy-MM-dd")
    private Date scxgrq;

    /** 是否删除  */
    private String sfsc;

    /** 身高 */
    private String sg;

    /** 失访原因 */
    private String sfsf;

    /** 是否失访 */
    private String sfyy;

    /** 图像保存路径 */
    private String imgUrl;

    /** 下一步管理措施 */
    private String xybglcs;

    /** 转诊结果 */
    private String jg;

    /** 备注 */
    private String bz;

    /** stzz */
    private String stzz;

    /** 联系人及电话 */
    private String lxrjdh;

    /** 联系人及其电话 */
    private String zzlxdh;

    /** 居民签名 */
    private String jmqz;

    /** 居民id */
    private String jmid;

    /** 拍照取证 */
    private String pzqz;

    /** 是否两周内随访 */
    private String istwoweek;

    /** 目前用药情况 */
    private String mqyyqk;

    /** 用药调整意见 */
    private String yytzyj;

    /** 机构 */
    private String zzorg;

    /** 转诊科室 */
    private String zzkb;

    /** 转诊联系人 */
    private String zzlxr;

    /** 转诊结果 */
    private String zzjg;

    /** 目前用药 */
    private List<HcpMbDrugDto> drugList;
    /** 用药调整 */
    private List<HcpMbDrugDto> tzdrugList;
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

    public String getTjbh() {
        return tjbh;
    }

    public void setTjbh(String tjbh) {
        this.tjbh = tjbh;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }

    public String getHzbh() {
        return hzbh;
    }

    public void setHzbh(String hzbh) {
        this.hzbh = hzbh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Date getSfrq() {
        return sfrq;
    }

    public void setSfrq(Date sfrq) {
        this.sfrq = sfrq;
    }

    public String getSfys() {
        return sfys;
    }

    public void setSfys(String sfys) {
        this.sfys = sfys;
    }

    public Date getXcsfrq() {
        return xcsfrq;
    }

    public void setXcsfrq(Date xcsfrq) {
        this.xcsfrq = xcsfrq;
    }

    public String getZzyw() {
        return zzyw;
    }

    public void setZzyw(String zzyw) {
        this.zzyw = zzyw;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getQtzz() {
        return qtzz;
    }

    public void setQtzz(String qtzz) {
        this.qtzz = qtzz;
    }

    public String getGxy() {
        return gxy;
    }

    public void setGxy(String gxy) {
        this.gxy = gxy;
    }

    public String getDxy() {
        return dxy;
    }

    public void setDxy(String dxy) {
        this.dxy = dxy;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTzzs() {
        return tzzs;
    }

    public void setTzzs(String tzzs) {
        this.tzzs = tzzs;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getRxyl() {
        return rxyl;
    }

    public void setRxyl(String rxyl) {
        this.rxyl = rxyl;
    }

    public String getRyjl() {
        return ryjl;
    }

    public void setRyjl(String ryjl) {
        this.ryjl = ryjl;
    }

    public String getMzydcs() {
        return mzydcs;
    }

    public void setMzydcs(String mzydcs) {
        this.mzydcs = mzydcs;
    }

    public String getMzydsj() {
        return mzydsj;
    }

    public void setMzydsj(String mzydsj) {
        this.mzydsj = mzydsj;
    }

    public String getSyqk() {
        return syqk;
    }

    public void setSyqk(String syqk) {
        this.syqk = syqk;
    }

    public String getXcsfsyl() {
        return xcsfsyl;
    }

    public void setXcsfsyl(String xcsfsyl) {
        this.xcsfsyl = xcsfsyl;
    }

    public String getXltz() {
        return xltz;
    }

    public void setXltz(String xltz) {
        this.xltz = xltz;
    }

    public String getZyxw() {
        return zyxw;
    }

    public void setZyxw(String zyxw) {
        this.zyxw = zyxw;
    }

    public String getFzjc() {
        return fzjc;
    }

    public void setFzjc(String fzjc) {
        this.fzjc = fzjc;
    }

    public String getFyycx() {
        return fyycx;
    }

    public void setFyycx(String fyycx) {
        this.fyycx = fyycx;
    }

    public String getYwblfy() {
        return ywblfy;
    }

    public void setYwblfy(String ywblfy) {
        this.ywblfy = ywblfy;
    }

    public String getBlfyms() {
        return blfyms;
    }

    public void setBlfyms(String blfyms) {
        this.blfyms = blfyms;
    }

    public String getSffl() {
        return sffl;
    }

    public void setSffl(String sffl) {
        this.sffl = sffl;
    }

    public String getZzyy() {
        return zzyy;
    }

    public void setZzyy(String zzyy) {
        this.zzyy = zzyy;
    }

    public String getSffs() {
        return sffs;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs;
    }

    public String getXcsftz() {
        return xcsftz;
    }

    public void setXcsftz(String xcsftz) {
        this.xcsftz = xcsftz;
    }

    public String getXcsftzzs() {
        return xcsftzzs;
    }

    public void setXcsftzzs(String xcsftzzs) {
        this.xcsftzzs = xcsftzzs;
    }

    public String getXcsfrxyl() {
        return xcsfrxyl;
    }

    public void setXcsfrxyl(String xcsfrxyl) {
        this.xcsfrxyl = xcsfrxyl;
    }

    public String getXcsfryjl() {
        return xcsfryjl;
    }

    public void setXcsfryjl(String xcsfryjl) {
        this.xcsfryjl = xcsfryjl;
    }

    public String getXcsfmzydcs() {
        return xcsfmzydcs;
    }

    public void setXcsfmzydcs(String xcsfmzydcs) {
        this.xcsfmzydcs = xcsfmzydcs;
    }

    public String getXcsfmcydsj() {
        return xcsfmcydsj;
    }

    public void setXcsfmcydsj(String xcsfmcydsj) {
        this.xcsfmcydsj = xcsfmcydsj;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public Date getJbrq() {
        return jbrq;
    }

    public void setJbrq(Date jbrq) {
        this.jbrq = jbrq;
    }

    public String getScxgr() {
        return scxgr;
    }

    public void setScxgr(String scxgr) {
        this.scxgr = scxgr;
    }

    public Date getScxgrq() {
        return scxgrq;
    }

    public void setScxgrq(Date scxgrq) {
        this.scxgrq = scxgrq;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getSfsf() {
        return sfsf;
    }

    public void setSfsf(String sfsf) {
        this.sfsf = sfsf;
    }

    public String getSfyy() {
        return sfyy;
    }

    public void setSfyy(String sfyy) {
        this.sfyy = sfyy;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getXybglcs() {
        return xybglcs;
    }

    public void setXybglcs(String xybglcs) {
        this.xybglcs = xybglcs;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getStzz() {
        return stzz;
    }

    public void setStzz(String stzz) {
        this.stzz = stzz;
    }

    public String getLxrjdh() {
        return lxrjdh;
    }

    public void setLxrjdh(String lxrjdh) {
        this.lxrjdh = lxrjdh;
    }

    public String getZzlxdh() {
        return zzlxdh;
    }

    public void setZzlxdh(String zzlxdh) {
        this.zzlxdh = zzlxdh;
    }

    public String getJmqz() {
        return jmqz;
    }

    public void setJmqz(String jmqz) {
        this.jmqz = jmqz;
    }

    public String getJmid() {
        return jmid;
    }

    public void setJmid(String jmid) {
        this.jmid = jmid;
    }

    public String getPzqz() {
        return pzqz;
    }

    public void setPzqz(String pzqz) {
        this.pzqz = pzqz;
    }

    public String getIstwoweek() {
        return istwoweek;
    }

    public void setIstwoweek(String istwoweek) {
        this.istwoweek = istwoweek;
    }

    public String getMqyyqk() {
        return mqyyqk;
    }

    public void setMqyyqk(String mqyyqk) {
        this.mqyyqk = mqyyqk;
    }

    public String getYytzyj() {
        return yytzyj;
    }

    public void setYytzyj(String yytzyj) {
        this.yytzyj = yytzyj;
    }

    public String getZzorg() {
        return zzorg;
    }

    public void setZzorg(String zzorg) {
        this.zzorg = zzorg;
    }

    public String getZzkb() {
        return zzkb;
    }

    public void setZzkb(String zzkb) {
        this.zzkb = zzkb;
    }

    public String getZzlxr() {
        return zzlxr;
    }

    public void setZzlxr(String zzlxr) {
        this.zzlxr = zzlxr;
    }

    public String getZzjg() {
        return zzjg;
    }

    public void setZzjg(String zzjg) {
        this.zzjg = zzjg;
    }

    public List<HcpMbDrugDto> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<HcpMbDrugDto> drugList) {
        this.drugList = drugList;
    }

    public List<HcpMbDrugDto> getTzdrugList() {
        return tzdrugList;
    }

    public void setTzdrugList(List<HcpMbDrugDto> tzdrugList) {
        this.tzdrugList = tzdrugList;
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
