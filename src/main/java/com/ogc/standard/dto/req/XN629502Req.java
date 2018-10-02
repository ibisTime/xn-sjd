package com.ogc.standard.dto.req;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629502Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 必填，编号
    @NotBlank
    private String code;

    // 必填，更新人
    @NotBlank
    private String updater;

    // 选填，名称
    private String name;

    // 选填，分类
    private String type;

    // 选填，图片
    private String pic;

    // 必填，价格
    @NotBlank
    private BigDecimal price;

    // 必填，描述
    @NotBlank
    private String description;

    // 必填，有效时长(单位小时)
    @NotBlank
    private Integer validityTerm;

    // 选填，状态（0上架/1下架）
    private String status;

    // 选填，序号
    private String orderNo;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValidityTerm() {
        return validityTerm;
    }

    public void setValidityTerm(Integer validityTerm) {
        this.validityTerm = validityTerm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
