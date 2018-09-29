package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMaintainProjectAO;
import com.ogc.standard.bo.IMaintainProjectBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MaintainProject;
import com.ogc.standard.exception.BizException;

@Service
public class MaintainProjectAOImpl implements IMaintainProjectAO {

    @Autowired
    private IMaintainProjectBO maintainProjectBO;

    @Override
    public String addMaintainProject(String maintainId, String projectName,
            String description, String updater, String remark) {

        return maintainProjectBO.saveMaintainProject(maintainId, projectName,
            description, updater, remark);

    }

    @Override
    public void editMaintainProject(String code, String projectName,
            String description, String updater, String remark) {
        if (!maintainProjectBO.isMaintainProjectExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        maintainProjectBO.refreshMaintainProject(code, projectName, description,
            updater, remark);
    }

    @Override
    public void dropMaintainProject(String code) {
        if (!maintainProjectBO.isMaintainProjectExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        maintainProjectBO.removeMaintainProject(code);
    }

    @Override
    public Paginable<MaintainProject> queryMaintainProjectPage(int start,
            int limit, MaintainProject condition) {
        return maintainProjectBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<MaintainProject> queryMaintainProjectList(
            MaintainProject condition) {
        return maintainProjectBO.queryMaintainProjectList(condition);
    }

    @Override
    public MaintainProject getMaintainProject(String code) {
        return maintainProjectBO.getMaintainProject(code);
    }
}
