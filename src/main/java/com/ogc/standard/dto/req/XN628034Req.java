package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删帖(oss)
 * @author: silver 
 * @since: 2018年8月22日 下午3:55:46 
 * @history:
 */
public class XN628034Req {
    // 编号
    @NotBlank
    private String code;

    // 更新人
    @NotBlank
    private String updater;

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

}
