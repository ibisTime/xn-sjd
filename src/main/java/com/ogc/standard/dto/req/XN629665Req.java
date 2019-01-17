package com.ogc.standard.dto.req;

/**
 * 分页查审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 4:01:02 PM 
 * @history:
 */
public class XN629665Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 类型（产权方上架）
    private String type;

    // 名称
    private String name;

    // 电话
    private String mobile;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
