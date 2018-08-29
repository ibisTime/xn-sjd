/**
 * @Title XN802250Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:13:30 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:13:30 
 * @history:
 */
public class XN802002Req {

    // 英文简称
    @NotBlank
    private String id;

    // 英文名称
    @NotBlank
    private String ename;

    // 中文名称
    @NotBlank
    private String cname;

    // 币种图标
    @NotBlank
    private String icon;

    // 钱包水印图标
    @NotBlank
    private String pic1;

    // 流水加钱图标
    @NotBlank
    private String pic2;

    // 流水减钱图标
    @NotBlank
    private String pic3;

    // UI序号
    @NotBlank
    private String orderNo;

    // 归集阀值
    @NotBlank
    private String collectStart;

    // 取现手续费
    @NotBlank
    private String withdrawFee;

    // 操作人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCollectStart() {
        return collectStart;
    }

    public void setCollectStart(String collectStart) {
        this.collectStart = collectStart;
    }

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
