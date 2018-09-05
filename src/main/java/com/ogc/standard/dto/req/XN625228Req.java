package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据昵称查询广告
 * @author: taojian 
 * @since: 2018年9月5日 下午10:56:03 
 * @history:
 */
public class XN625228Req {

    // 昵称
    @NotBlank
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
