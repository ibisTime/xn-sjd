package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.ogc.standard.dto.res.XN629720Res;

/**
 * @author: taojian 
 * @since: 2018年11月6日 下午3:34:14 
 * @history:
 */
public class XN629720Req {

    // 订单金额
    @NotBlank
    private String amount;

    // 商品数量
    @NotBlank
    private String quantity;

    // 下单人
    @NotBlank
    private String applyUser;

    // 下单说明
    private String applynote;

    // 配送方式
    @NotBlank
    private String expressType;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    // 明细列表
    @NotBlank
    private List<XN629720Res> detailList;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplynote() {
        return applynote;
    }

    public void setApplynote(String applynote) {
        this.applynote = applynote;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
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

    public List<XN629720Res> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<XN629720Res> detailList) {
        this.detailList = detailList;
    }

}
