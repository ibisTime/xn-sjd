package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增弹幕
 * @author: silver 
 * @since: Nov 23, 2018 3:16:05 PM 
 * @history:
 */
public class XN629381Req {

    // 编号
    @NotBlank
    private String code;

    // 内容
    @NotBlank
    private String content;

    // 图片
    private String pic;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
