package com.java.moudle.bphs.follow.dto;

public class HcpMbDrugDto implements Cloneable {
    /** 药物名称 */
    private String ywmc;

    /** 药物用量 */
    private String ywjl;

    /** 药物频次 */
    private String ywpc;

    /** 药物单位 */
    private String ywdw;

    /** 药物用法 */
    private String ywyf;

    /** 随访id */
    private String sfid;

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getYwjl() {
        return ywjl;
    }

    public void setYwjl(String ywjl) {
        this.ywjl = ywjl;
    }

    public String getYwpc() {
        return ywpc;
    }

    public void setYwpc(String ywpc) {
        this.ywpc = ywpc;
    }

    public String getYwdw() {
        return ywdw;
    }

    public void setYwdw(String ywdw) {
        this.ywdw = ywdw;
    }

    public String getYwyf() {
        return ywyf;
    }

    public void setYwyf(String ywyf) {
        this.ywyf = ywyf;
    }

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
