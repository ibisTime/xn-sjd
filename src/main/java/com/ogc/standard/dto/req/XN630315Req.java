/**
 * @Title XN630315Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:16:07 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 分页查询部门
 * @author: tao 
 * @since: 2018年8月18日 上午10:16:07 
 * @history:
 */
public class XN630315Req extends APageReq {

    private static final long serialVersionUID = -6483191516296829301L;

    // 公司编号
    @NotBlank
    private String companyCode;

    // 部门名字
    private String name;

    // 部门负责人
    private String leader;

    // 负责人手机
    private String leaderMobile;

    // 上级部门编号
    private String parentCode;

    // 更新人
    private String updater;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderMobile() {
        return leaderMobile;
    }

    public void setLeaderMobile(String leaderMobile) {
        this.leaderMobile = leaderMobile;
    }
}
