package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置权重(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午4:59:26 
 * @history:
 */
public class XN628010Req {
    // 战队编号
    @NotBlank
    private String code;

    // 权重
    @NotBlank
    private String weight;

    // 更新人
    @NotBlank
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
