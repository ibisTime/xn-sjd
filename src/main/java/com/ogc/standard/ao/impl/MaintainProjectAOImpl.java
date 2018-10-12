package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMaintainProjectAO;
import com.ogc.standard.bo.IMaintainProjectBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MaintainProject;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.exception.BizException;

@Service
public class MaintainProjectAOImpl implements IMaintainProjectAO {

    @Autowired
    private IMaintainProjectBO maintainProjectBO;

    @Autowired
    private ISYSUserBO sysUserBO;

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
        Paginable<MaintainProject> page = maintainProjectBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (MaintainProject maintainProject : page.getList()) {
                initMaintainProject(maintainProject);
            }
        }

        return page;
    }

    @Override
    public List<MaintainProject> queryMaintainProjectList(
            MaintainProject condition) {
        return maintainProjectBO.queryMaintainProjectList(condition);
    }

    @Override
    public MaintainProject getMaintainProject(String code) {
        MaintainProject maintainProject = maintainProjectBO
            .getMaintainProject(code);

        initMaintainProject(maintainProject);

        return maintainProject;
    }

    private void initMaintainProject(MaintainProject maintainProject) {
        // 养护人信息
        SYSUser maintain = sysUserBO
            .getSYSUserUnCheck(maintainProject.getMaintainId());
        maintainProject.setMaintainInfo(maintain);

        // 更新人
        String updaterName = null;
        SYSUser updater = sysUserBO
            .getSYSUserUnCheck(maintainProject.getUpdater());
        if (null != updater) {
            updaterName = updater.getMobile();
            if (StringUtils.isNotBlank(updater.getRealName())) {
                updaterName = updater.getRealName().concat("-")
                    .concat(updaterName);
            }
        }
        maintainProject.setUpdaterName(updaterName);

    }
}
