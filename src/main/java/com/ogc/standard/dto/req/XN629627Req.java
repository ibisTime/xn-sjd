package com.ogc.standard.dto.req;

/**
 * 列表查询养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629627Req extends AListReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 养护方编号
    private String maintainId;

    // 项目名称
    private String projectName;

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
