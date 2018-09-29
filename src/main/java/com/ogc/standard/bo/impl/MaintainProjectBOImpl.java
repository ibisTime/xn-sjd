package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IMaintainProjectBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IMaintainProjectDAO;
import com.ogc.standard.domain.MaintainProject;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class MaintainProjectBOImpl extends PaginableBOImpl<MaintainProject>
        implements IMaintainProjectBO {

    @Autowired
    private IMaintainProjectDAO maintainProjectDAO;

    @Override
    public boolean isMaintainProjectExist(String code) {
        MaintainProject condition = new MaintainProject();
        condition.setCode(code);
        if (maintainProjectDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveMaintainProject(String maintainId, String projectName,
            String description, String updater, String remark) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.MaintainProject.getCode());

        MaintainProject data = new MaintainProject();
        data.setCode(code);
        data.setMaintainId(maintainId);
        data.setProjectName(projectName);
        data.setDescription(description);
        data.setUpdater(updater);

        data.setRemark(remark);
        data.setUpdateDatetime(new Date());
        maintainProjectDAO.insert(data);
        return code;
    }

    @Override
    public void removeMaintainProject(String code) {
        if (StringUtils.isNotBlank(code)) {
            MaintainProject data = new MaintainProject();
            data.setCode(code);
            maintainProjectDAO.delete(data);
        }
    }

    @Override
    public void refreshMaintainProject(String code, String projectName,
            String description, String updater, String remark) {
        MaintainProject data = new MaintainProject();
        data.setCode(code);
        data.setProjectName(projectName);
        data.setDescription(description);
        data.setUpdater(updater);

        data.setRemark(remark);
        data.setUpdateDatetime(new Date());
        maintainProjectDAO.updateMaintainProject(data);
    }

    @Override
    public List<MaintainProject> queryMaintainProjectList(
            MaintainProject condition) {
        return maintainProjectDAO.selectList(condition);
    }

    @Override
    public MaintainProject getMaintainProject(String code) {
        MaintainProject data = null;
        if (StringUtils.isNotBlank(code)) {
            MaintainProject condition = new MaintainProject();
            condition.setCode(code);
            data = maintainProjectDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "养护项目不存在！");
            }
        }
        return data;
    }
}
