package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:11:07 
 * @history:
 */
public class XN629344Req {

    // 编号
    @NotBlank
    private String code;

    // 操作人
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
