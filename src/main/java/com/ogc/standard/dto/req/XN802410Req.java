package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 分红
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:50 
 * @history:
 */
public class XN802410Req {

    // 必填，分红id
    @NotBlank
    private String id;

    // 必填，可分配利润
    @NotBlank
    private String divideProfit;

    // 必填，分红人
    @NotBlank
    private String divideUser;

    // 选填，备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivideProfit() {
        return divideProfit;
    }

    public void setDivideProfit(String divideProfit) {
        this.divideProfit = divideProfit;
    }

    public String getDivideUser() {
        return divideUser;
    }

    public void setDivideUser(String divideUser) {
        this.divideUser = divideUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
