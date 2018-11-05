package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:58 AM 
 * @history:
 */
public class XN629405Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    @NotBlank
    private String code;

    // 更新人
    private String updater;

    public String getCode() {
        return code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
