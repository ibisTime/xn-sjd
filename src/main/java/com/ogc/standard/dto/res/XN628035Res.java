package com.ogc.standard.dto.res;

/**
 * 评论帖子
 * @author: silver 
 * @since: 2018年8月24日 上午10:31:15 
 * @history:
 */
public class XN628035Res {
    // true
    private Boolean code;

    // 关键字过滤状态（0=未过滤，1=关键字被替换，2=待审核）
    private String filterFlag;

    public XN628035Res(Boolean code, String filterFlag) {
        super();
        this.code = code;
        this.filterFlag = filterFlag;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
        this.code = code;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

}
