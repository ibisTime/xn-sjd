package com.ogc.standard.dto.req;

/**
 * 列表查询道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629517Req extends AListReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 选填，类型（0保护罩/1一件收取）
    private String type;

    // 选填，状态（0上架/1下架）
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
