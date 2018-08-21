package com.ogc.standard.dto.res;

public class XN805041Res {

    private String userId;

    private String userReferee;

    // 是否是注册用户
    private boolean isRegister;

    public XN805041Res() {
    }

    public XN805041Res(String userId, Boolean isRegister, String userReferee) {
        this.userId = userId;
        this.isRegister = isRegister;
        this.userReferee = userReferee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean isRegister) {
        this.isRegister = isRegister;
    }

}
