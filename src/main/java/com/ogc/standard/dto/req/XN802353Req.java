package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 广播
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:02:36 
 * @history:
 */
public class XN802353Req {

    // 取现订单编号(必填)
    @NotBlank
    private String code;

    // 散取地址编id号(币种为以太坊时必填)
    private Long mAddressId;

    // 操作人(必填)
    @NotBlank
    private String approveUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getmAddressId() {
        return mAddressId;
    }

    public void setmAddressId(Long mAddressId) {
        this.mAddressId = mAddressId;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

}
