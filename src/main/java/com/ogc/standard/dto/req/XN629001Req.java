package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:35:33 
 * @history:
 */
public class XN629001Req extends BaseReq {

    private static final long serialVersionUID = -7320303500219417597L;

    // 编号
    @NotBlank
    private String code;

    // 上级编号
    @NotBlank
    private String parentCode;

    // 名称
    @NotBlank
    private String name;

    // 图片
    private String pic;

    // 顺序
    @NotBlank
    private String orderNo;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
