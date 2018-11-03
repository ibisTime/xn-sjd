package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 预售权
* @author: silver 
* @since: 2018-11-03 17:46:42
* @history:
*/
public class PresellInventory extends ABaseDO {

    private static final long serialVersionUID = 5675937159720283012L;

    // 编号
    private String code;

    // 组类型
    private String groupType;

    // 组编号
    private String groupCode;

    // 树木编号
    private String treeNumber;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getTreeNumber() {
        return treeNumber;
    }

}
