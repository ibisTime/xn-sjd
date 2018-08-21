
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午1:12:28 
 * @history:
 */
public class XN805241Req {
    // 记录ID
    @NotBlank
    private String id;

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
