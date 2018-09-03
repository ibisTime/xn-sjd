package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/22.
 */
public class XN625229Req {

    @NotBlank
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
