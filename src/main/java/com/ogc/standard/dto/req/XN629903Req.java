package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提现金额统计
 * @author: silver 
 * @since: Oct 22, 2018 3:54:30 PM 
 * @history:
 */
public class XN629903Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 申请人
    @NotBlank
    private String applyUser;

    // 状态列表
    private List<String> statusList;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
