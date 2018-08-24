package com.ogc.standard.dto.req;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午3:38:40 
 * @history:
 */
public class XN650057Req extends APageReq {

    private static final long serialVersionUID = 1008764902858165436L;

    // 选填，委托单编号
    private String code;

    // 选填，组合编号
    private String groupCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

}
