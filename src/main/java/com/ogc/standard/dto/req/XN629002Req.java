package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上架产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:35:33 
 * @history:
 */
public class XN629002Req extends BaseReq {
    private static final long serialVersionUID = -3095678471054371491L;

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
