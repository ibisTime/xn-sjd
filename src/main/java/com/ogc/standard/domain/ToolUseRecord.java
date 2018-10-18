package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 使用记录
* @author: lei
* @since: 2018年10月03日 上午10:46:44
* @history:
*/
public class ToolUseRecord extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 道具编号
    private String toolCode;

    // 道具订单编号
    private String toolOrderCode;

    // 认养权编号
    private String adoptTreeCode;

    // 道具类型（0保护罩/1一件收取）
    private String toolType;

    // 状态(1生效中 0已失效)
    private String status;

    // 使用人
    private String userId;

    // 使用时间
    private Date createDatetime;

    // 失效时间
    private Date invalidDatetime;

    // ************************* DB properties *************************

    // 道具订单
    private ToolOrder toolOrderInfo;

    // 认养权
    private AdoptOrderTree adoptTreeInfo;

    // 用户信息
    private User userInfo;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setToolOrderCode(String toolOrderCode) {
        this.toolOrderCode = toolOrderCode;
    }

    public String getToolOrderCode() {
        return toolOrderCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public ToolOrder getToolOrderInfo() {
        return toolOrderInfo;
    }

    public void setToolOrderInfo(ToolOrder toolOrderInfo) {
        this.toolOrderInfo = toolOrderInfo;
    }

    public AdoptOrderTree getAdoptTreeInfo() {
        return adoptTreeInfo;
    }

    public void setAdoptTreeInfo(AdoptOrderTree adoptTreeInfo) {
        this.adoptTreeInfo = adoptTreeInfo;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

}
