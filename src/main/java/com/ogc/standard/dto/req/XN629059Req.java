package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询我的集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午10:51:36 
 * @history:
 */
public class XN629059Req extends APageReq {

    private static final long serialVersionUID = -585586450144233268L;

    // 用户编号
    @NotBlank
    private String userId;

    // 状态
    private List<String> statusList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
