package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.MaintainProject;

/**
 * 养护项目
 * @author: silver 
 * @since: 2018年9月28日 下午8:13:57 
 * @history:
 */
public interface IMaintainProjectBO extends IPaginableBO<MaintainProject> {

    public boolean isMaintainProjectExist(String code);

    // 添加养护项目
    public String saveMaintainProject(String maintainId, String projectName,
            String Description, String updater, String remark);

    // 删除养护项目
    public void removeMaintainProject(String code);

    // 修改养护项目
    public void refreshMaintainProject(String code, String projectName,
            String Description, String updater, String remark);

    public List<MaintainProject> queryMaintainProjectList(
            MaintainProject condition);

    public MaintainProject getMaintainProject(String code);

}
