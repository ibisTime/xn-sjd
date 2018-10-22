package com.ogc.standard.dto.res;

/**
 * 收取碳泡泡
 * @author: silver 
 * @since: Oct 22, 2018 5:29:58 PM 
 * @history:
 */
public class XN629350Res {
    // 错误代码
    private String resCode;

    // 错误描述
    private String resInfo;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    public XN629350Res(String resCode, String resInfo) {
        super();
        this.resCode = resCode;
        this.resInfo = resInfo;
    }

}
