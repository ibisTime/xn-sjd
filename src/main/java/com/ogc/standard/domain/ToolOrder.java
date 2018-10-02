package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 道具订单
* @author: lei
* @since: 2018年10月02日 下午09:51:21
* @history:
*/
public class ToolOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 道具编号
    private String toolCode;

    // 道具名称
    private String toolName;

    // 道具图片
    private String toolPic;

    // 购买价格
    private BigDecimal price;

    // 购买时长(单位小时)
    private Integer validityTerm;

    // 购买人
    private String userId;

    // 购买时间
    private Date createDatetime;

    // ************************* DB properties *************************

    // 用户信息
    private User userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolPic() {
        return toolPic;
    }

    public void setToolPic(String toolPic) {
        this.toolPic = toolPic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getValidityTerm() {
        return validityTerm;
    }

    public void setValidityTerm(Integer validityTerm) {
        this.validityTerm = validityTerm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

}
