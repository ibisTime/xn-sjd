package com.ogc.standard.dto.req;

/**
 * 分页查询养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629635Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 古树编号
    private String treeNumber;

    // 养护项目编号
    private String projectCode;

    // 养护人编号
    private String maintainerCode;

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getMaintainerCode() {
        return maintainerCode;
    }

    public void setMaintainerCode(String maintainerCode) {
        this.maintainerCode = maintainerCode;
    }

}
