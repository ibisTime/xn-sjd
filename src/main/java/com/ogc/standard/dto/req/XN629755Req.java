/**
 * @Title XN629750Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午4:43:37 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.List;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午4:43:37 
 * @history:
 */
public class XN629755Req extends APageReq {

    private static final long serialVersionUID = -1303947203940719028L;

    private String commodityCode;

    private String userId;

    private String parentCode;

    private String parentUserId;

    private String status;

    private List<String> statusList;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
