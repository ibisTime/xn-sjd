package com.ogc.standard.dto.req;

/**
 * 列表养护人
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629617Req extends AListReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 养护方编号
    private String maintainId;

    // 养护人姓名
    private String name;

    // 养护人电话
    private String mobile;

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
