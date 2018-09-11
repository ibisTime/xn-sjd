package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 详情查询承兑订单
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625286Req extends APageReq {

    private static final long serialVersionUID = 3913281104492731431L;

    // 必填，编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
