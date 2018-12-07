package com.ogc.standard.dto.res;

/**
 * 评论评论(front)
 * @author: silver 
 * @since: 2018年8月24日 上午10:31:15 
 * @history:
 */
public class XN629730Res {
    // true
    private String code;

    // 关键字过滤状态（0=未过滤，1=关键字被替换，2=待审核）
    private String filterFlag;

    public XN629730Res(String code, String filterFlag) {
        super();
        this.code = code;
        this.filterFlag = filterFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

}
