package com.java.moudle.bphs.follow.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HcpMxTnbDto {

    private String id;

    /** 体检编号 */
    private String tjbh;

    /** 患者编号  */
    private String hzbh;

    /** 档案号  */
    private String dah;

    /** 身份证号  */
    private String sfzh;

    /** 患者姓名  */
    private String hzxm;

    /** 随访日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date sfrq;

    /** 随访医生  */
    private String sfys;

    /** 下次随访日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date xcsfrq;

    private String zzyw;//症状有无

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

    /** 体质指数  */
    private String tzzs;

    /** 足背动脉搏动  */
    private String zbdmbd;

    /** 其他体征症状  */
    private String qttzzz;

    /** 日吸烟量  */
    private String rxyl;

    /** 日饮酒量  */
    private String ryjl;

    /** 每周运动次数  */
    private String mzydcs;

    /** 每次运动时间  */
    private String mcydsj;

    /** 主食每天多少克  */
    private String zsmtdsk;

    /** 心理调整  */
    private String xltz;

    /** 遵医行为  */
    private String zyxw;

    /** 空腹血糖值  */
    private String kfxtz;

    /** 糖化血红蛋白  */
    private String thxhdb;

    /** 检查日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date jcrq;

    /** 辅助检查  */
    private String fzjc;

    /** 服药依从性  */
    private String fyycx;

    /** 药物不良反应  */
    private String ywblfy;

    /** 不良反应描述  */
    private String blfyms;

    /** 低血糖反应  */
    private String dxtfy;

    /** 随访分类  */
    private String sffl;

    /** 随访方式  */
    private String sffs;

    /** 转诊原因  */
    private String zzyy;

    /** 转诊科别 */
    private String zzkb;

    /** 下次随访日吸烟量目标  */
    private String xcsfrxylmb;

    /** 下次随访日饮酒量目标  */
    private String xcsfryjlmb;

    /** 下次随访每周运动次数目标  */
    private String xcsfmzydcsmb;

    /** 下次随访每次运动时间目标  */
    private String xcsfmcydsjmb;

    /** 建表人  */
    private String jbr;

    /** 建表日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date jbrq;

    /** 上一次修改人  */
    private String scxgr;

    /** 上一次修改日期  */
    @JSONField(format="yyyy-MM-dd")
    private Date scxgrq;

    /** 是否删除  */
    private String sfsc;

    /** 足背动脉搏动消失减弱标示 */
    private String zbdmbdbs;

    /** 下次随访主食目标 */
    private String xcsfzsmb;

    /** 身高 */
    private String sg;

    /** 失访原因 */
    private String sfsf;

    /** 是否失访 */
    private String sfyy;

    /** 转诊机构 */
    private String zzorg;

    /** 转诊联系人 */
    private String zzlxr;

    /** 下一步管理措施 */
    private String xybglcs;

    /** 转诊结果 */
    private String zzjg;

    /** 转诊联系电话 */
    private String zzlxdh;

    /** 备注 */
    private String bz;

    /** 居民id */
    private String jmid;

    /** 目前用药情况 */
    private String mqyyqk;

    /** 低血糖反应其他 */
    private String dxtqi;

    /** 用药调整意见 */
    private String yytzyj;

    /** 目前用药胰岛素种类 */
    private String mqydszl;

    /** 目前用药胰岛素用量 */
    private String mqydsyl;

    /** 目前用药胰岛素频次 */
    private String mqydspc;

    /** 调整用药胰岛素种类 */
    private String tzydszl;

    /** 调整用药胰岛素用量 */
    private String tzydsyl;

    /** 调整用药胰岛素频次 */
    private String tzydspc;

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

    public String getHzbh() {
        return hzbh;
    }

    public void setHzbh(String hzbh) {
        this.hzbh = hzbh;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
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

    public String getZbdmbd() {
        return zbdmbd;
    }

    public void setZbdmbd(String zbdmbd) {
        this.zbdmbd = zbdmbd;
    }

    public String getQttzzz() {
        return qttzzz;
    }

    public void setQttzzz(String qttzzz) {
        this.qttzzz = qttzzz;
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

    public String getMcydsj() {
        return mcydsj;
    }

    public void setMcydsj(String mcydsj) {
        this.mcydsj = mcydsj;
    }

    public String getZsmtdsk() {
        return zsmtdsk;
    }

    public void setZsmtdsk(String zsmtdsk) {
        this.zsmtdsk = zsmtdsk;
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

    public String getKfxtz() {
        return kfxtz;
    }

    public void setKfxtz(String kfxtz) {
        this.kfxtz = kfxtz;
    }

    public String getThxhdb() {
        return thxhdb;
    }

    public void setThxhdb(String thxhdb) {
        this.thxhdb = thxhdb;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
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

    public String getDxtfy() {
        return dxtfy;
    }

    public void setDxtfy(String dxtfy) {
        this.dxtfy = dxtfy;
    }

    public String getSffl() {
        return sffl;
    }

    public void setSffl(String sffl) {
        this.sffl = sffl;
    }

    public String getSffs() {
        return sffs;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs;
    }

    public String getZzyy() {
        return zzyy;
    }

    public void setZzyy(String zzyy) {
        this.zzyy = zzyy;
    }

    public String getZzkb() {
        return zzkb;
    }

    public void setZzkb(String zzkb) {
        this.zzkb = zzkb;
    }

    public String getXcsfrxylmb() {
        return xcsfrxylmb;
    }

    public void setXcsfrxylmb(String xcsfrxylmb) {
        this.xcsfrxylmb = xcsfrxylmb;
    }

    public String getXcsfryjlmb() {
        return xcsfryjlmb;
    }

    public void setXcsfryjlmb(String xcsfryjlmb) {
        this.xcsfryjlmb = xcsfryjlmb;
    }

    public String getXcsfmzydcsmb() {
        return xcsfmzydcsmb;
    }

    public void setXcsfmzydcsmb(String xcsfmzydcsmb) {
        this.xcsfmzydcsmb = xcsfmzydcsmb;
    }

    public String getXcsfmcydsjmb() {
        return xcsfmcydsjmb;
    }

    public void setXcsfmcydsjmb(String xcsfmcydsjmb) {
        this.xcsfmcydsjmb = xcsfmcydsjmb;
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

    public String getZbdmbdbs() {
        return zbdmbdbs;
    }

    public void setZbdmbdbs(String zbdmbdbs) {
        this.zbdmbdbs = zbdmbdbs;
    }

    public String getXcsfzsmb() {
        return xcsfzsmb;
    }

    public void setXcsfzsmb(String xcsfzsmb) {
        this.xcsfzsmb = xcsfzsmb;
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

    public String getZzorg() {
        return zzorg;
    }

    public void setZzorg(String zzorg) {
        this.zzorg = zzorg;
    }

    public String getZzlxr() {
        return zzlxr;
    }

    public void setZzlxr(String zzlxr) {
        this.zzlxr = zzlxr;
    }

    public String getXybglcs() {
        return xybglcs;
    }

    public void setXybglcs(String xybglcs) {
        this.xybglcs = xybglcs;
    }

    public String getZzjg() {
        return zzjg;
    }

    public void setZzjg(String zzjg) {
        this.zzjg = zzjg;
    }

    public String getZzlxdh() {
        return zzlxdh;
    }

    public void setZzlxdh(String zzlxdh) {
        this.zzlxdh = zzlxdh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getJmid() {
        return jmid;
    }

    public void setJmid(String jmid) {
        this.jmid = jmid;
    }

    public String getMqyyqk() {
        return mqyyqk;
    }

    public void setMqyyqk(String mqyyqk) {
        this.mqyyqk = mqyyqk;
    }

    public String getDxtqi() {
        return dxtqi;
    }

    public void setDxtqi(String dxtqi) {
        this.dxtqi = dxtqi;
    }

    public String getYytzyj() {
        return yytzyj;
    }

    public void setYytzyj(String yytzyj) {
        this.yytzyj = yytzyj;
    }

    public String getMqydszl() {
        return mqydszl;
    }

    public void setMqydszl(String mqydszl) {
        this.mqydszl = mqydszl;
    }

    public String getMqydsyl() {
        return mqydsyl;
    }

    public void setMqydsyl(String mqydsyl) {
        this.mqydsyl = mqydsyl;
    }

    public String getMqydspc() {
        return mqydspc;
    }

    public void setMqydspc(String mqydspc) {
        this.mqydspc = mqydspc;
    }

    public String getTzydszl() {
        return tzydszl;
    }

    public void setTzydszl(String tzydszl) {
        this.tzydszl = tzydszl;
    }

    public String getTzydsyl() {
        return tzydsyl;
    }

    public void setTzydsyl(String tzydsyl) {
        this.tzydsyl = tzydsyl;
    }

    public String getTzydspc() {
        return tzydspc;
    }

    public void setTzydspc(String tzydspc) {
        this.tzydspc = tzydspc;
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
