package com.ogc.standard.dto.req;

/**
 * 删除审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 3:46:15 PM 
 * @history:
 */
public class XN629662Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 电话
    private String mobile;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
