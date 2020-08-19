package com.java.moudle.bphs.follow.dto;


import com.java.until.DateUtils;

import java.util.Date;

public class Mxtnbsfb {

	private static final long serialVersionUID = 1L;

	private String id;//
	private String jmid;
	private String tjbh;// 体检编号
	private String hzbh;// 患者编号
	private String jlh; // 记录号
	private String dah;// 档案号
	private String sfzh;// 身份证号
	private String hzxm;// 患者姓名
	private Date sfrq;// 随访日期
	public String sfrqStr;// 随访日期
	private String sfys;// 随访医生
	private String sfysmc;// 随访医生姓名
	private Date xcsfrq;// 下次随访日期
	public String xcsfrqStr;// 下次随访日期
	private String zz;// 症状
	private String qtzz;// 其他症状
	private String gxy;// 高血压
	private String dxy;// 低血压
	private String tz;// 体重
	private String tzzs;// 体质指数
	private String zbdmbd;// 足背动脉搏动
	private String qtzz1;// 其他症状
	private String rxyl;// 日吸烟量
	private String ryjl;// 日饮酒量
	private String mzydcs;// 每周运动次数
	private String mcydsj;// 每次运动时间
	private String zsmtdsk;// 主食每天多少克
	private String xltz;// 心理调整
	private String zyxw;// 遵医行为
	private String kfxtz;// 空腹血糖值
	private String thxhdb;// 糖化血红蛋白
	private Date jcrq;// 检查日期
	public String jcrqStr;// 检查日期
	private String fzjc;// 辅助检查
	private String fyycx;// 服药依从性
	private String ywblfy;// 药物不良反应
	private String blfyms;// 不良反应描述
	private String dxtfy;// 低血糖反应
	private String sffl;// 随访分类
	private String ydszl;// 胰岛素种类
	private String ydsyfhyl;// 胰岛素用法和用量
	private String sffs;// 随访方式
	private String zzyy;// 转诊原因
	private String jgjks;// 机构及科室
	private String xcsftzmb;// 下次随访目标体重
	private String xcsftzzsmb;// 下次随访体质指数目标
	private String xcsfrxylmb;// 下次随访日吸烟量目标
	private String xcsfryjlmb;// 下次随访日饮酒量目标
	private String xcsfmzydcsmb;// 下次随访每周运动次数目标
	private String xcsfmcydsjmb;// 下次随访每次运动时间目标
	private String jbr;// 建表人
	private Date jbrq;// 建表日期
	private String scxgr;// 上一次修改人
	private Date scxgrq;// 上一次修改日期
	private String sfsc;// 是否删除
	private String xcsfzsmb;// 下次随访主食目标
	private String ywmc1;// 药物名称1
	private String ywmc1ci;// 药物名称1每日几次
	private String ywmc1mg;// 药物名称1第次多少mg
	private String ywmc2;// 药物名称2
	private String ywmc2ci;// 药物名称2每日几次
	private String ywmc2mg;// 药物名称2第次多少mg
	private String ywmc3;// 药物名称3
	private String ywmc3ci;// 药物名称3每日几次
	private String ywmc3mg;// 药物名称3第次多少mg
	private String importDeviceNum;// 导入设备号
	private Date importTime;// 导入时间
	private String importUser;// 导入人
	private String importIp;// 导入IP
	private String synFlg;// 是否同步 0 否 1 是
	private String zdyydw1;
	private String zdyydw2;
	private String zdyydw3;
	private String imgUrl;
	private String ysqm;
	private String jmqm;
	private String pzqz;
	private String qt;
	private String qtjc;
	private String sg;
	private String ywdwkhp1;
	private String ywdwkhp2;
	private String ywdwkhp3;

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

	public String getPzqz() {
		return pzqz;
	}

	public void setPzqz(String pzqz) {
		this.pzqz = pzqz;
	}

	public String getQtjc() {
		return qtjc;
	}

	public void setQtjc(String qtjc) {
		this.qtjc = qtjc;
	}

	public String getYwdwkhp2() {
		return ywdwkhp2;
	}

	public void setYwdwkhp2(String ywdwkhp2) {
		this.ywdwkhp2 = ywdwkhp2;
	}

	public String getYwdwkhp3() {
		return ywdwkhp3;
	}

	public void setYwdwkhp3(String ywdwkhp3) {
		this.ywdwkhp3 = ywdwkhp3;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getYwdwkhp1() {
		return ywdwkhp1;
	}

	public void setYwdwkhp1(String ywdwkhp1) {
		this.ywdwkhp1 = ywdwkhp1;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getZdyydw1() {
		return zdyydw1;
	}

	public void setZdyydw1(String zdyydw1) {
		this.zdyydw1 = zdyydw1;
	}

	public String getZdyydw2() {
		return zdyydw2;
	}

	public void setZdyydw2(String zdyydw2) {
		this.zdyydw2 = zdyydw2;
	}

	public String getZdyydw3() {
		return zdyydw3;
	}

	public void setZdyydw3(String zdyydw3) {
		this.zdyydw3 = zdyydw3;
	}

	public String getJlh() {
		return jlh;
	}

	public void setJlh(String jlh) {
		this.jlh = jlh;
	}

	public String getJmid() {
		return jmid;
	}

	public void setJmid(String jmid) {
		this.jmid = jmid;
	}

	public String getSfysmc() {
		return sfysmc;
	}

	public void setSfysmc(String sfysmc) {
		this.sfysmc = sfysmc;
	}

	public String getTjbh() {
		return tjbh;
	}

	public void setTjbh(String tjbh) {
		this.tjbh = tjbh;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHzbh() {
		return this.hzbh;
	}

	public void setHzbh(String hzbh) {
		this.hzbh = hzbh;
	}

	public String getDah() {
		return this.dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getHzxm() {
		return this.hzxm;
	}

	public void setHzxm(String hzxm) {
		this.hzxm = hzxm;
	}

	public Date getSfrq() {
		return this.sfrq;
	}

	public void setSfrq(Date sfrq) {
		this.sfrqStr = DateUtils.formatDate(sfrq);
		this.sfrq = sfrq;
	}

	public String getSfys() {
		return this.sfys;
	}

	public void setSfys(String sfys) {
		this.sfys = sfys;
	}

	public Date getXcsfrq() {
		return this.xcsfrq;
	}

	public void setXcsfrq(Date xcsfrq) {
		this.xcsfrqStr = DateUtils.formatDate(xcsfrq);
		this.xcsfrq = xcsfrq;
	}

	public String getZz() {
		return this.zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getQtzz() {
		return this.qtzz;
	}

	public void setQtzz(String qtzz) {
		this.qtzz = qtzz;
	}

	public String getGxy() {
		return this.gxy;
	}

	public void setGxy(String gxy) {
		this.gxy = gxy;
	}

	public String getDxy() {
		return this.dxy;
	}

	public void setDxy(String dxy) {
		this.dxy = dxy;
	}

	public String getTz() {
		return this.tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getTzzs() {
		return this.tzzs;
	}

	public void setTzzs(String tzzs) {
		this.tzzs = tzzs;
	}

	public String getZbdmbd() {
		return this.zbdmbd;
	}

	public void setZbdmbd(String zbdmbd) {
		this.zbdmbd = zbdmbd;
	}

	public String getQtzz1() {
		return this.qtzz1;
	}

	public void setQtzz1(String qtzz1) {
		this.qtzz1 = qtzz1;
	}

	public String getRxyl() {
		return this.rxyl;
	}

	public void setRxyl(String rxyl) {
		this.rxyl = rxyl;
	}

	public String getRyjl() {
		return this.ryjl;
	}

	public void setRyjl(String ryjl) {
		this.ryjl = ryjl;
	}

	public String getMzydcs() {
		return this.mzydcs;
	}

	public void setMzydcs(String mzydcs) {
		this.mzydcs = mzydcs;
	}

	public String getMcydsj() {
		return this.mcydsj;
	}

	public void setMcydsj(String mcydsj) {
		this.mcydsj = mcydsj;
	}

	public String getZsmtdsk() {
		return this.zsmtdsk;
	}

	public void setZsmtdsk(String zsmtdsk) {
		this.zsmtdsk = zsmtdsk;
	}

	public String getXltz() {
		return this.xltz;
	}

	public void setXltz(String xltz) {
		this.xltz = xltz;
	}

	public String getZyxw() {
		return this.zyxw;
	}

	public void setZyxw(String zyxw) {
		this.zyxw = zyxw;
	}

	public String getKfxtz() {
		return this.kfxtz;
	}

	public void setKfxtz(String kfxtz) {
		this.kfxtz = kfxtz;
	}

	public String getThxhdb() {
		return this.thxhdb;
	}

	public void setThxhdb(String thxhdb) {
		this.thxhdb = thxhdb;
	}

	public Date getJcrq() {
		return this.jcrq;
	}

	public void setJcrq(Date jcrq) {
		this.jcrqStr = DateUtils.formatDate(jcrq);
		this.jcrq = jcrq;
	}

	public String getFzjc() {
		return this.fzjc;
	}

	public void setFzjc(String fzjc) {
		this.fzjc = fzjc;
	}

	public String getFyycx() {
		return this.fyycx;
	}

	public void setFyycx(String fyycx) {
		this.fyycx = fyycx;
	}

	public String getYwblfy() {
		return this.ywblfy;
	}

	public void setYwblfy(String ywblfy) {
		this.ywblfy = ywblfy;
	}

	public String getBlfyms() {
		return this.blfyms;
	}

	public void setBlfyms(String blfyms) {
		this.blfyms = blfyms;
	}

	public String getDxtfy() {
		return this.dxtfy;
	}

	public void setDxtfy(String dxtfy) {
		this.dxtfy = dxtfy;
	}

	public String getSffl() {
		return this.sffl;
	}

	public void setSffl(String sffl) {
		this.sffl = sffl;
	}

	public String getYdszl() {
		return this.ydszl;
	}

	public void setYdszl(String ydszl) {
		this.ydszl = ydszl;
	}

	public String getYdsyfhyl() {
		return this.ydsyfhyl;
	}

	public void setYdsyfhyl(String ydsyfhyl) {
		this.ydsyfhyl = ydsyfhyl;
	}

	public String getSffs() {
		return this.sffs;
	}

	public void setSffs(String sffs) {
		this.sffs = sffs;
	}

	public String getZzyy() {
		return this.zzyy;
	}

	public void setZzyy(String zzyy) {
		this.zzyy = zzyy;
	}

	public String getJgjks() {
		return this.jgjks;
	}

	public void setJgjks(String jgjks) {
		this.jgjks = jgjks;
	}

	public String getXcsftzmb() {
		return this.xcsftzmb;
	}

	public void setXcsftzmb(String xcsftzmb) {
		this.xcsftzmb = xcsftzmb;
	}

	public String getXcsftzzsmb() {
		return this.xcsftzzsmb;
	}

	public void setXcsftzzsmb(String xcsftzzsmb) {
		this.xcsftzzsmb = xcsftzzsmb;
	}

	public String getXcsfrxylmb() {
		return this.xcsfrxylmb;
	}

	public void setXcsfrxylmb(String xcsfrxylmb) {
		this.xcsfrxylmb = xcsfrxylmb;
	}

	public String getXcsfryjlmb() {
		return this.xcsfryjlmb;
	}

	public void setXcsfryjlmb(String xcsfryjlmb) {
		this.xcsfryjlmb = xcsfryjlmb;
	}

	public String getXcsfmzydcsmb() {
		return this.xcsfmzydcsmb;
	}

	public void setXcsfmzydcsmb(String xcsfmzydcsmb) {
		this.xcsfmzydcsmb = xcsfmzydcsmb;
	}

	public String getXcsfmcydsjmb() {
		return this.xcsfmcydsjmb;
	}

	public void setXcsfmcydsjmb(String xcsfmcydsjmb) {
		this.xcsfmcydsjmb = xcsfmcydsjmb;
	}

	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public Date getJbrq() {
		return this.jbrq;
	}

	public void setJbrq(Date jbrq) {
		this.jbrq = jbrq;
	}

	public String getScxgr() {
		return this.scxgr;
	}

	public void setScxgr(String scxgr) {
		this.scxgr = scxgr;
	}

	public Date getScxgrq() {
		return this.scxgrq;
	}

	public void setScxgrq(Date scxgrq) {
		this.scxgrq = scxgrq;
	}

	public String getSfsc() {
		return this.sfsc;
	}

	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}

	public String getXcsfzsmb() {
		return this.xcsfzsmb;
	}

	public void setXcsfzsmb(String xcsfzsmb) {
		this.xcsfzsmb = xcsfzsmb;
	}

	public String getYwmc1() {
		return this.ywmc1;
	}

	public void setYwmc1(String ywmc1) {
		this.ywmc1 = ywmc1;
	}

	public String getYwmc1ci() {
		return this.ywmc1ci;
	}

	public void setYwmc1ci(String ywmc1ci) {
		this.ywmc1ci = ywmc1ci;
	}

	public String getYwmc1mg() {
		return this.ywmc1mg;
	}

	public void setYwmc1mg(String ywmc1mg) {
		this.ywmc1mg = ywmc1mg;
	}

	public String getYwmc2() {
		return this.ywmc2;
	}

	public void setYwmc2(String ywmc2) {
		this.ywmc2 = ywmc2;
	}

	public String getYwmc2ci() {
		return this.ywmc2ci;
	}

	public void setYwmc2ci(String ywmc2ci) {
		this.ywmc2ci = ywmc2ci;
	}

	public String getYwmc2mg() {
		return this.ywmc2mg;
	}

	public void setYwmc2mg(String ywmc2mg) {
		this.ywmc2mg = ywmc2mg;
	}

	public String getYwmc3() {
		return this.ywmc3;
	}

	public void setYwmc3(String ywmc3) {
		this.ywmc3 = ywmc3;
	}

	public String getYwmc3ci() {
		return this.ywmc3ci;
	}

	public void setYwmc3ci(String ywmc3ci) {
		this.ywmc3ci = ywmc3ci;
	}

	public String getYwmc3mg() {
		return this.ywmc3mg;
	}

	public void setYwmc3mg(String ywmc3mg) {
		this.ywmc3mg = ywmc3mg;
	}

	public String getImportDeviceNum() {
		return this.importDeviceNum;
	}

	public void setImportDeviceNum(String importDeviceNum) {
		this.importDeviceNum = importDeviceNum;
	}

	public Date getImportTime() {
		return this.importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public String getImportUser() {
		return this.importUser;
	}

	public void setImportUser(String importUser) {
		this.importUser = importUser;
	}

	public String getImportIp() {
		return this.importIp;
	}

	public void setImportIp(String importIp) {
		this.importIp = importIp;
	}

	public String getSynFlg() {
		return this.synFlg;
	}

	public void setSynFlg(String synFlg) {
		this.synFlg = synFlg;
	}
}
