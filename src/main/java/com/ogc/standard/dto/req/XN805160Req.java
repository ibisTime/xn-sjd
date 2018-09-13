package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请认证
 * @author: taojian 
 * @since: 2018年9月13日 下午5:33:39 
 * @history:
 */
public class XN805160Req {
    // 国家
    @NotBlank
    private String country;

    // 正面证件照
    @NotBlank
    private String idFace;

    // 背面证件照
    @NotBlank
    private String idOppo;

    // 手持证件照
    private String idHold;

    // 证件类型
    @NotBlank
    private String idKind;

    // 证件号码
    @NotBlank
    private String idNo;

    // 真实姓名
    @NotBlank
    private String realName;

    // 申请人
    @NotBlank
    private String applyUser;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdFace() {
        return idFace;
    }

    public void setIdFace(String idFace) {
        this.idFace = idFace;
    }

    public String getIdOppo() {
        return idOppo;
    }

    public void setIdOppo(String idOppo) {
        this.idOppo = idOppo;
    }

    public String getIdHold() {
        return idHold;
    }

    public void setIdHold(String idHold) {
        this.idHold = idHold;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
}
