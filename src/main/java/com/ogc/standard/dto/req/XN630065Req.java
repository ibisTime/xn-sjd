package com.ogc.standard.dto.req;

public class XN630065Req extends APageReq {

    private static final long serialVersionUID = -7049202319583663210L;

    // （选填）用户名
    private String realName;

    // （选填）角色
    private String roleCode;

    public String getRealName() {
        return realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
