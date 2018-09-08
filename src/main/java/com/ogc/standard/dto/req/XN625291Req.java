package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/13.
 * 
 * @author: lei 
 * @since: 2018年9月8日 下午10:21:48 
 * @history:
 */
public class XN625291Req {
    @NotBlank
    private String coin;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

}
