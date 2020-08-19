package com.java.moudle.bphs.archive.dto;

import com.java.until.DictUtil;

public class HmXqDto {

    /**
     * 基本信息 表
     */
    private String activity = "0";//档案完整度
    private String ywgms;// 药物过敏史
    private String cjqk;// 残疾情况
    private String cjqkms;// 残疾情况描述
    private String hzmc;// 患者名称
    private String ys;// 医生
    private String xb;// 性别
    private String csrq;// 出生日期
    private String lxr;// 联系人
    private String lxdh;// 联系电话
    private String xuexing;// 血型
    private String sj;// 手机
    private String qtfkfs;// 其他付款方式
    private String qtgmfy;// 其他过敏反应
    private String qtjbms;// 其他疾病描述
    private String diseasenditionex;//
    private String khbh;// 客户ID
    private String xzz;// 现住址
    private String jtzz;// 家庭住址
    private String jdjg;// 建档机构
    private String mz;// 民族
    private String bls;// 暴露史
    private String jdr;// 建档人
    private String jdrq;// 建档日期
    private String jdrx;//建档结束日期
    private String sycxgr;// 上一次修改人员
    private String zhxgrq;// 最后修改日期
    private String rqlx;// 人群类型
    private String jkfl;//健康分类
    private String jtkbh;// 家庭卡编号
    private String hzgx;// 户主关系
    private String qthzgx;// 其他户主关系
    private String dzyx;// 电子邮箱
    private String sfsc;// 是否删除
    private String sfxg;// 是否更新
    private String xzmc;// 乡镇名称
    private String czmc;// 村庄名称
    private String jdjgmc;// 建档机构名称
    private String jdrxm;// 建档人姓名
    private String fwmc;// 房屋名称
    private String jgmc;// 机构名称
    private String id;//
    private String jlh;// 记录号
    private String zjlx;//证件类型
    private String sfzh;// 身份证号
    private String sheng;// 省
    private String sbh;// 市
    private String dqbh;// 地区编号
    private String xzbh;// 乡镇
    private String cz;// 村庄
    private String ssshengbh;// 省
    private String sssbh;// 市
    private String ssxbh;// 县
    private String ssxzbh;// 乡镇
    private String sscbh;// 村庄
    private String gzdw;// 工作单位
    private String orgId;// 机构ID
    private String areaId;// 行政区划ID
    private String jzlx;// 居住类型
    private String guoji;// 民族
    private String rh;// rh血型
    private String whcd;// 文化程度
    private String gz;// 工作
    private String hyqk;// 婚姻情况
    private String zffs;// 支付方式
    private String imgUrl;// 头像
    private String nl;
    private String jtrs;
    private String jdrqs;
    private String ehrId;
    private String ysid;
    private String sfpk;
    private String code;
    private long createTime;
    private long updateTime;
    private String jmid;
    private String jtid;//家庭id
    private String hzxm;//户主姓名
    private String jzqk;//居住情况
    private String jtrks;//家庭人口
    private String jtjg;//家庭结构
    private String hzsfzh;//户主身份证号

    /**
     * 病史信息表
     */
    private String jblx;//   疾病类型
    private String jbmc;//   疾病名称
    private String exzl;//   恶性肿瘤
    private String qtjbs;//   其他疾病史
    private String zybs;//   职业病史
    private String qtjbmc;//   其他疾病名称
    private String qzsj;//   确诊时间
    private String sick = "无";
    private String operation = "无";
    private String trauma = "无";
    private String blood = "无";

    public String getJmid() {
		return jmid;
	}

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public void setJmid(String jmid) {
		this.jmid = jmid;
	}

	public String getSfpk() {
      return sfpk;
    }

    public void setSfpk(String sfpk) {
      this.sfpk = sfpk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJkfl() {
        return jkfl;
    }

    public void setJkfl(String jkfl) {
        this.jkfl = jkfl;
    }

    public String getYsid() {
        return ysid;
    }

    public void setYsid(String ysid) {
        this.ysid = ysid;
    }

    public String getJdrqs() {
        return jdrqs;
    }

    public void setJdrqs(String jdrqs) {
        this.jdrqs = jdrqs;
    }

    public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTrauma() {
		return trauma;
	}

	public void setTrauma(String trauma) {
		this.trauma = trauma;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}



	private String jzs;//   家族史
    private String fqbs;//   父亲病史
    private String fqdqtbs;//   父亲的其他病史
    private String mqbs;//   母亲病史
    private String mqdqtbs;//   母亲的其他病史
    private String xdjmdbs;//   兄弟姐妹的病史
    private String xdjmdqtbs;//   兄弟姐妹的其他病史
    private String znbs;//   子女病史
    private String znqtbs;//   子女其他病史

    /**
     * 生活环境表
     */
    private String dabh;//   档案编号
    private String cfpfcs;//   厨房排风措施
    private String rllx;//   燃料类型
    private String yslx;//   饮水类型
    private String qxl;//   禽畜栏
    private String cslx;//   厕所类型
    private String dah;//档案编号

    

	public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }


    public String getJtid() {
		return jtid;
	}

	public void setJtid(String jtid) {
		this.jtid = jtid;
	}

	public String getYwgms() {
        return ywgms;
    }

    public void setYwgms(String ywgms) {
        this.ywgms = DictUtil.getDictValue("ywgms", ywgms);
    }

    public String getCjqk() {
        return cjqk;
    }

    public void setCjqk(String cjqk) {
        this.cjqk = DictUtil.getDictValue("cjqk", cjqk);
    }

    public String getCjqkms() {
        return cjqkms;
    }

    public void setCjqkms(String cjqkms) {
        this.cjqkms = cjqkms;
    }

    public String getHzmc() {
        return hzmc;
    }

    public void setHzmc(String hzmc) {
        this.hzmc = hzmc;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = DictUtil.getDictValue("xb", xb);
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getXuexing() {
        return xuexing;
    }

    public void setXuexing(String xuexing) {
        this.xuexing = DictUtil.getDictValue("da-xx", xuexing);
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getQtfkfs() {
        return qtfkfs;
    }

    public void setQtfkfs(String qtfkfs) {
        this.qtfkfs = qtfkfs;
    }

    public String getQtgmfy() {
        return qtgmfy;
    }

    public void setQtgmfy(String qtgmfy) {
        this.qtgmfy = qtgmfy;
    }

    public String getQtjbms() {
        return qtjbms;
    }

    public void setQtjbms(String qtjbms) {
        this.qtjbms = qtjbms;
    }

    public String getDiseasenditionex() {
        return diseasenditionex;
    }

    public void setDiseasenditionex(String diseasenditionex) {
        this.diseasenditionex = diseasenditionex;
    }

    public String getKhbh() {
        return khbh;
    }

    public void setKhbh(String khbh) {
        this.khbh = khbh;
    }

    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    public String getJtzz() {
        return jtzz;
    }

    public void setJtzz(String jtzz) {
        this.jtzz = jtzz;
    }

    public String getJdjg() {
        return jdjg;
    }

    public void setJdjg(String jdjg) {
        this.jdjg = jdjg;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = DictUtil.getDictValue("mz", mz);
    }

    public String getBls() {
        return bls;
    }

    public void setBls(String bls) {
        this.bls = DictUtil.getDictValue("bls", bls);
    }

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
    }

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }

    public String getJdrx() {
        return jdrx;
    }

    public void setJdrx(String jdrx) {
        this.jdrx = jdrx;
    }

    public String getSycxgr() {
        return sycxgr;
    }

    public void setSycxgr(String sycxgr) {
        this.sycxgr = sycxgr;
    }

    public String getZhxgrq() {
        return zhxgrq;
    }

    public void setZhxgrq(String zhxgrq) {
        this.zhxgrq = zhxgrq;
    }

    public String getRqlx() {
        return rqlx;
    }

    public void setRqlx(String rqlx) {
        this.rqlx = DictUtil.getDictValue("rqfl", rqlx);
    }

    public String getJtkbh() {
        return jtkbh;
    }

    public void setJtkbh(String jtkbh) {
        this.jtkbh = jtkbh;
    }

    public String getHzgx() {
        return hzgx;
    }

    public void setHzgx(String hzgx) {
        this.hzgx = hzgx;
    }

    public String getQthzgx() {
        return qthzgx;
    }

    public void setQthzgx(String qthzgx) {
        this.qthzgx = qthzgx;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getSfxg() {
        return sfxg;
    }

    public void setSfxg(String sfxg) {
        this.sfxg = sfxg;
    }

    public String getXzmc() {
        return xzmc;
    }

    public void setXzmc(String xzmc) {
        this.xzmc = xzmc;
    }

    public String getCzmc() {
        return czmc;
    }

    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }

    public String getJdjgmc() {
        return jdjgmc;
    }

    public void setJdjgmc(String jdjgmc) {
        this.jdjgmc = jdjgmc;
    }

    public String getJdrxm() {
        return jdrxm;
    }

    public void setJdrxm(String jdrxm) {
        this.jdrxm = jdrxm;
    }

    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJlh() {
        return jlh;
    }

    public void setJlh(String jlh) {
        this.jlh = jlh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getSbh() {
        return sbh;
    }

    public void setSbh(String sbh) {
        this.sbh = sbh;
    }

    public String getDqbh() {
        return dqbh;
    }

    public void setDqbh(String dqbh) {
        this.dqbh = dqbh;
    }

    public String getXzbh() {
        return xzbh;
    }

    public void setXzbh(String xzbh) {
        this.xzbh = xzbh;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getSsshengbh() {
        return ssshengbh;
    }

    public void setSsshengbh(String ssshengbh) {
        this.ssshengbh = ssshengbh;
    }

    public String getSssbh() {
        return sssbh;
    }

    public void setSssbh(String sssbh) {
        this.sssbh = sssbh;
    }

    public String getSsxbh() {
        return ssxbh;
    }

    public void setSsxbh(String ssxbh) {
        this.ssxbh = ssxbh;
    }

    public String getSsxzbh() {
        return ssxzbh;
    }

    public void setSsxzbh(String ssxzbh) {
        this.ssxzbh = ssxzbh;
    }

    public String getSscbh() {
        return sscbh;
    }

    public void setSscbh(String sscbh) {
        this.sscbh = sscbh;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getJzlx() {
        return jzlx;
    }

    public void setJzlx(String jzlx) {
        this.jzlx = DictUtil.getDictValue("czlx", jzlx);
    }

    public String getGuoji() {
        return guoji;
    }

    public void setGuoji(String guoji) {
        this.guoji = guoji;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = DictUtil.getDictValue("da-RHyx", rh);
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = DictUtil.getDictValue("whcd", whcd);
    }

    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = DictUtil.getDictValue("zy", gz);
    }

    public String getHyqk() {
        return hyqk;
    }

    public void setHyqk(String hyqk) {
        this.hyqk = DictUtil.getDictValue("hyzk", hyqk);
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = DictUtil.getDictValue("ylfyzffs", zffs);
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getJtrs() {
        return jtrs;
    }

    public void setJtrs(String jtrs) {
        this.jtrs = jtrs;
    }

    public String getCslx() {
        return cslx;
    }

    public void setCslx(String cslx) {
        this.cslx = DictUtil.getDictValue("da-shhj-cs", cslx);
    }

    public String getJblx() {
        return jblx;
    }

    public void setJblx(String jblx) {
        if (jblx.indexOf("1") >= 0) {
            this.sick = "有";
        }
        if (jblx.indexOf("有") >= 0) {
            this.operation = "1";
        }
        if (jblx.indexOf("3") >= 0) {
            this.trauma = "有";
        }
        if (jblx.indexOf("4") >= 0) {
            this.blood = "有";
        }
        this.jblx = jblx;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
    	if("1".equals(jblx)) {
			this.jblx = DictUtil.getDictValue("da-jws-jb", jbmc);
		}else {
			this.jblx = jbmc;
		}
    }

    public String getExzl() {
        return exzl;
    }

    public void setExzl(String exzl) {
        this.exzl = exzl;
    }

    public String getQtjbs() {
        return qtjbs;
    }

    public void setQtjbs(String qtjbs) {
        this.qtjbs = qtjbs;
    }

    public String getZybs() {
        return zybs;
    }

    public void setZybs(String zybs) {
        this.zybs = zybs;
    }

    public String getQtjbmc() {
        return qtjbmc;
    }

    public void setQtjbmc(String qtjbmc) {
        this.qtjbmc = qtjbmc;
    }

    public String getQzsj() {
        return qzsj;
    }

    public void setQzsj(String qzsj) {
        this.qzsj = qzsj;
    }

    public String getJzs() {
        return jzs;
    }

    public void setJzs(String jzs) {
        this.jzs = jzs;
    }

    public String getFqbs() {
        return fqbs;
    }

    public void setFqbs(String fqbs) {
        this.fqbs = DictUtil.getDictValue("da-jzs-fq", fqbs);
    }

    public String getFqdqtbs() {
        return fqdqtbs;
    }

    public void setFqdqtbs(String fqdqtbs) {
        this.fqdqtbs = fqdqtbs;
    }

    public String getMqbs() {
        return mqbs;
    }

    public void setMqbs(String mqbs) {
        this.mqbs = DictUtil.getDictValue("da-jzs-mq", mqbs);
    }

    public String getMqdqtbs() {
        return mqdqtbs;
    }

    public void setMqdqtbs(String mqdqtbs) {
        this.mqdqtbs = mqdqtbs;
    }

    public String getXdjmdbs() {
        return xdjmdbs;
    }

    public void setXdjmdbs(String xdjmdbs) {
        this.xdjmdbs = DictUtil.getDictValue("da-jzs-xdjm", xdjmdbs);
    }

    public String getXdjmdqtbs() {
        return xdjmdqtbs;
    }

    public void setXdjmdqtbs(String xdjmdqtbs) {
        this.xdjmdqtbs = xdjmdqtbs;
    }

    public String getZnbs() {
        return znbs;
    }

    public void setZnbs(String znbs) {
        this.znbs = DictUtil.getDictValue("da-jzs-zn", znbs);
    }

    public String getZnqtbs() {
        return znqtbs;
    }

    public void setZnqtbs(String znqtbs) {
        this.znqtbs = znqtbs;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getCfpfcs() {
        return cfpfcs;
    }

    public void setCfpfcs(String cfpfcs) {
        this.cfpfcs = DictUtil.getDictValue("da-shhj-cfpfss", cfpfcs);
    }

    public String getRllx() {
        return rllx;
    }

    public void setRllx(String rllx) {
        this.rllx = DictUtil.getDictValue("da-shhj-rllx", rllx);
    }

    public String getYslx() {
        return yslx;
    }

    public void setYslx(String yslx) {
        this.yslx = DictUtil.getDictValue("da-shhj-ys", yslx);
    }

    public String getQxl() {
        return qxl;
    }

    public void setQxl(String qxl) {
        this.qxl = DictUtil.getDictValue("da-shhj-qxl", qxl);
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

	public String getHzxm() {
		return hzxm;
	}

	public void setHzxm(String hzxm) {
		this.hzxm = hzxm;
	}

	public String getJzqk() {
		return jzqk;
	}

	public void setJzqk(String jzqk) {
		this.jzqk = jzqk;
	}

	public String getJtrks() {
		return jtrks;
	}

	public void setJtrks(String jtrks) {	
		this.jtrks = jtrks;
	}

	public String getJtjg() {
		return jtjg;
	}

	public void setJtjg(String jtjg) {
		this.jtjg = jtjg;
	}

	public String getHzsfzh() {
		return hzsfzh;
	}

	public void setHzsfzh(String hzsfzh) {
		this.hzsfzh = hzsfzh;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getEhrId() {
		return ehrId;
	}

	public void setEhrId(String ehrId) {
		this.ehrId = ehrId;
	}

}
