package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 个人认证
 * @author: silver 
 * @since: Nov 24, 2018 1:07:44 PM 
 * @history:
 */
public class XN805071Req {

    // userId（必填）
    @NotBlank
    private String userId;

    @NotBlank
    private String realName;

    @NotBlank
    private String idNo;

    private String idPic;

    private String backIdPic;

    // 自我介绍
    private String introduce;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdPic() {
        return idPic;
    }

    public void setIdPic(String idPic) {
        this.idPic = idPic;
    }

    public String getBackIdPic() {
        return backIdPic;
    }

    public void setBackIdPic(String backIdPic) {
        this.backIdPic = backIdPic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

}
