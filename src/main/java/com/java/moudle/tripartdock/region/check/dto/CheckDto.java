package com.java.moudle.tripartdock.region.check.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: Check
 * @Description: 体检-基本信息
 * @author Administrator
 * @date 2019年7月30日
 */
public class CheckDto implements Serializable {

	private static final long serialVersionUID = 7504549679034744003L;
	
	@Id
	private String id;//
	private String khbh;// 客户编号
	private String sfzh;// 身份证号
	@JSONField(format = "yyyy-MM-dd")
	private Date jcrq;// 检查日期
	@Column(name = "ys")
	private String ys;// 医生
	private String tjfl; // 体检分类(4.15-64岁城乡居民 5.65-79岁城乡居民 6.80岁及以上城乡居民 7.企业退休人员 8.公职、事业单位（退休人员）、企业职工// 9.流动人口)
	private String zz;// 症状
	private String qt;// 其他
	private String tjbh;// 体检编号
	@Column(name = "jbr")
	private String jbr;// 建表人
	@JSONField(format = "yyyy-MM-dd")
	private Date jbrq;// 建表日期
	@Column(name = "sycxgr")
	private String sycxgr;// 上一次修改人
	@JSONField(format = "yyyy-MM-dd")
	private Date sycxgrq;// 上一次修改日期
	private String sfsc;// 是否删除
	private String dataSource;// 数据来源 1平台 2平板 3手机
	private String dataSourceNo;// 数据来源唯一标志
	private String importDeviceNum;// 导入设备号
	@JSONField(format = "yyyy-MM-dd")
	private Date importTime;// 导入时间
	private String importUser;// 导入人
	private String importIp;// 导入IP
	private String synFlg = "0";// 是否同步 0 否 1 是
	private String synId;// 同步ID
	@JSONField(format = "yyyy-MM-dd")
	private Date synTime;// 同步时间
	private String tjdw;// 体检单位
	private String updateFlg;// 是否修改标志
	private String imgUrl;// 图片路径
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	@JSONField(format = "yyyy-MM-dd")
	private Date updateTime;
	
	// 生活方式
	private ShfsDto shfs;
	// 健康检查
	private JkjcDto jkjc;
	// 辅助检查
	private XzjcjlbDto xzjcjlb;
	// 一般症状
	private YbzzbDto ybzzb;
	// 脏器功能
	private ZqgnbDto zqgnb;
	// 健康问题
	private JkwtbDto jkwtb;
	// 评估指导
	private PgzdjlbDto pgzdjlb;
	// 家族病床史
	private List<JzbcsDto> jzbcs;
	// 接种史
	private List<JzsDto> jzs;
	// 药剂记录
	private List<YjjlbDto> yjjlb;
	// 住院史
	private List<ZysDto> zys;
	
	private String fileRecordId;// 上传图像返回值
	private String healthExamNo;// 下载冠新返回值

	private String zzys;// 症状医师签名
	private String ctys;// 查体医师签名
	private String fkrqz;// 反馈人签字
	private Date fksj;// 2019-01-18
	private String fkys;// 妇科医师签名
	private String gjtpys;// 宫颈涂片医师签名
	private String gmzzys;// 肛门指诊医师签名
	private String jkpjys;// 健康评价医师签名
	private String jkzdys;// 健康指导医师签名
	private String qzjs;// 家长签字
	private String rxys;// 乳腺医师签名
	private String shfsys;// 生活方式医师签名
	private String sys;// 肝/肾/血脂医师签名
	private String xdtys;// 心电图医生
	private String xpys;// 胸部X线片医师签名
	private String xxys;// 血类医师签名
	private String ybqkys;// 一般状况医师签名
	private String ydys;// 眼底医师签名
	private String zqgnys;// 脏器功能医师签名
	private String zyqkys;// 健康问题/住院情况医师签名
	private String bchaoqtys;// B超其他医师签名
	private String bchaoys;// 腹部B超医师签名
	private String yyqkys;// 药剂接种医生
	private String qz;// 本人签字
	private String brqzimg; //手写板本人签字Base64
	
	private String name;
	private String zzText;
	
	
	public String getBrqzimg() {
		return brqzimg;
	}

	public void setBrqzimg(String brqzimg) {
		this.brqzimg = brqzimg;
	}

	public String getYyqkys() {
		return yyqkys;
	}

	public void setYyqkys(String yyqkys) {
		this.yyqkys = yyqkys;
	}

	public String getBchaoqtys() {
		return bchaoqtys;
	}

	public void setBchaoqtys(String bchaoqtys) {
		this.bchaoqtys = bchaoqtys;
	}

	public String getBchaoys() {
		return bchaoys;
	}

	public void setBchaoys(String bchaoys) {
		this.bchaoys = bchaoys;
	}

	public String getCtys() {
		return ctys;
	}

	public void setCtys(String ctys) {
		this.ctys = ctys;
	}

	public String getFkrqz() {
		return fkrqz;
	}

	public void setFkrqz(String fkrqz) {
		this.fkrqz = fkrqz;
	}

	public Date getFksj() {
		return fksj;
	}

	public void setFksj(Date fksj) {
		this.fksj = fksj;
	}

	public String getFkys() {
		return fkys;
	}

	public void setFkys(String fkys) {
		this.fkys = fkys;
	}

	public String getGjtpys() {
		return gjtpys;
	}

	public void setGjtpys(String gjtpys) {
		this.gjtpys = gjtpys;
	}

	public String getGmzzys() {
		return gmzzys;
	}

	public void setGmzzys(String gmzzys) {
		this.gmzzys = gmzzys;
	}

	public String getJkpjys() {
		return jkpjys;
	}

	public void setJkpjys(String jkpjys) {
		this.jkpjys = jkpjys;
	}

	public String getJkzdys() {
		return jkzdys;
	}

	public void setJkzdys(String jkzdys) {
		this.jkzdys = jkzdys;
	}

	public String getQz() {
		return qz;
	}

	public void setQz(String qz) {
		this.qz = qz;
	}

	public String getQzjs() {
		return qzjs;
	}

	public void setQzjs(String qzjs) {
		this.qzjs = qzjs;
	}

	public String getRxys() {
		return rxys;
	}

	public void setRxys(String rxys) {
		this.rxys = rxys;
	}

	public String getShfsys() {
		return shfsys;
	}

	public void setShfsys(String shfsys) {
		this.shfsys = shfsys;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getXdtys() {
		return xdtys;
	}

	public void setXdtys(String xdtys) {
		this.xdtys = xdtys;
	}

	public String getXpys() {
		return xpys;
	}

	public void setXpys(String xpys) {
		this.xpys = xpys;
	}

	public String getXxys() {
		return xxys;
	}

	public void setXxys(String xxys) {
		this.xxys = xxys;
	}

	public String getYbqkys() {
		return ybqkys;
	}

	public void setYbqkys(String ybqkys) {
		this.ybqkys = ybqkys;
	}

	public String getYdys() {
		return ydys;
	}

	public void setYdys(String ydys) {
		this.ydys = ydys;
	}

	public String getZqgnys() {
		return zqgnys;
	}

	public void setZqgnys(String zqgnys) {
		this.zqgnys = zqgnys;
	}

	public String getZyqkys() {
		return zyqkys;
	}

	public void setZyqkys(String zyqkys) {
		this.zyqkys = zyqkys;
	}

	public String getTjfl() {
		return tjfl;
	}

	public void setTjfl(String tjfl) {
		this.tjfl = tjfl;
	}

	public String getHealthExamNo() {
		return healthExamNo;
	}

	public void setHealthExamNo(String healthExamNo) {
		this.healthExamNo = healthExamNo;
	}

	public String getFileRecordId() {
		return fileRecordId;
	}

	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUpdateFlg() {
		return updateFlg;
	}

	public void setUpdateFlg(String updateFlg) {
		this.updateFlg = updateFlg;
	}

	public ShfsDto getShfs() {
		return shfs;
	}

	public void setShfs(ShfsDto shfs) {
		this.shfs = shfs;
	}

	public JkjcDto getJkjc() {
		return jkjc;
	}

	public void setJkjc(JkjcDto jkjc) {
		this.jkjc = jkjc;
	}

	public XzjcjlbDto getXzjcjlb() {
		return xzjcjlb;
	}

	public void setXzjcjlb(XzjcjlbDto xzjcjlb) {
		this.xzjcjlb = xzjcjlb;
	}

	public YbzzbDto getYbzzb() {
		return ybzzb;
	}

	public void setYbzzb(YbzzbDto ybzzb) {
		this.ybzzb = ybzzb;
	}

	public ZqgnbDto getZqgnb() {
		return zqgnb;
	}

	public void setZqgnb(ZqgnbDto zqgnb) {
		this.zqgnb = zqgnb;
	}

	public JkwtbDto getJkwtb() {
		return jkwtb;
	}

	public void setJkwtb(JkwtbDto jkwtb) {
		this.jkwtb = jkwtb;
	}

	public PgzdjlbDto getPgzdjlb() {
		return pgzdjlb;
	}

	public void setPgzdjlb(PgzdjlbDto pgzdjlb) {
		this.pgzdjlb = pgzdjlb;
	}

	public List<JzbcsDto> getJzbcs() {
		return jzbcs;
	}

	public void setJzbcs(List<JzbcsDto> jzbcs) {
		this.jzbcs = jzbcs;
	}

	public List<JzsDto> getJzs() {
		return jzs;
	}

	public void setJzs(List<JzsDto> jzs) {
		this.jzs = jzs;
	}

	public List<YjjlbDto> getYjjlb() {
		return yjjlb;
	}

	public void setYjjlb(List<YjjlbDto> yjjlb) {
		this.yjjlb = yjjlb;
	}

	public List<ZysDto> getZys() {
		return zys;
	}

	public void setZys(List<ZysDto> zys) {
		this.zys = zys;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKhbh() {
		return this.khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public Date getJcrq() {
		return this.jcrq;
	}

	public void setJcrq(Date jcrq) {
		this.jcrq = jcrq;
	}

	public String getYs() {
		return this.ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public String getZz() {
		return this.zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getQt() {
		return this.qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getTjbh() {
		return this.tjbh;
	}

	public void setTjbh(String tjbh) {
		this.tjbh = tjbh;
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

	public String getSycxgr() {
		return this.sycxgr;
	}

	public void setSycxgr(String sycxgr) {
		this.sycxgr = sycxgr;
	}

	public Date getSycxgrq() {
		return this.sycxgrq;
	}

	public void setSycxgrq(Date sycxgrq) {
		this.sycxgrq = sycxgrq;
	}

	public String getSfsc() {
		return this.sfsc;
	}

	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
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

	public String getTjdw() {
		return this.tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceNo() {
		return dataSourceNo;
	}

	public void setDataSourceNo(String dataSourceNo) {
		this.dataSourceNo = dataSourceNo;
	}

	public String getSynId() {
		return synId;
	}

	public void setSynId(String synId) {
		this.synId = synId;
	}

	public Date getSynTime() {
		return synTime;
	}

	public void setSynTime(Date synTime) {
		this.synTime = synTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getZzys() {
		return zzys;
	}

	public void setZzys(String zzys) {
		this.zzys = zzys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZzText() {
		return zzText;
	}

	public void setZzText(String zzText) {
		this.zzText = zzText;
	}

}
