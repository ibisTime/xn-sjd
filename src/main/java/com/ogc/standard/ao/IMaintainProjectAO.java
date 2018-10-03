package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MaintainProject;

public interface IMaintainProjectAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加养护项目
    public String addMaintainProject(String maintainId, String projectName,
            String description, String updater, String remark);

    // 删除养护项目
    public void dropMaintainProject(String code);

    // 修改养护项目
    public void editMaintainProject(String code, String projectName,
            String description, String updater, String remark);

    public Paginable<MaintainProject> queryMaintainProjectPage(int start,
            int limit, MaintainProject condition);

    public List<MaintainProject> queryMaintainProjectList(
            MaintainProject condition);

    public MaintainProject getMaintainProject(String code);

}
