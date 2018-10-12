package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMaintainRecordAO;
import com.ogc.standard.bo.IMaintainProjectBO;
import com.ogc.standard.bo.IMaintainRecordBO;
import com.ogc.standard.bo.IMaintainerBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MaintainProject;
import com.ogc.standard.domain.MaintainRecord;
import com.ogc.standard.domain.Maintainer;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN629630Req;
import com.ogc.standard.exception.BizException;

@Service
public class MaintainRecordAOImpl implements IMaintainRecordAO {

    @Autowired
    private IMaintainRecordBO maintainRecordBO;

    @Autowired
    private IMaintainerBO maintainerBO;

    @Autowired
    private IMaintainProjectBO maintainProjectBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public String addMaintainRecord(XN629630Req req) {
        Maintainer maintainer = maintainerBO
            .getMaintainer(req.getMaintainerCode());
        MaintainProject maintainProject = maintainProjectBO
            .getMaintainProject(req.getProjectCode());

        MaintainRecord data = new MaintainRecord();

        data.setTreeNumber(req.getTreeNumber());
        data.setMaintain(req.getUpdater());
        data.setProjectCode(req.getProjectCode());
        data.setProjectName(maintainProject.getProjectName());
        data.setMaintainerCode(req.getMaintainerCode());
        data.setMaintainerName(maintainer.getName());

        data.setPic(req.getPic());
        data.setDescription(req.getDescription());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        return maintainRecordBO.saveMaintainRecord(data);
    }

    @Override
    public void editMaintainRecord(String code, String pic, String description,
            String updater, String remark) {
        if (!maintainRecordBO.isMaintainRecordExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        maintainRecordBO.refreshMaintainRecord(code, pic, description, updater,
            remark);
    }

    @Override
    public void dropMaintainRecord(String code) {
        if (!maintainRecordBO.isMaintainRecordExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        maintainRecordBO.removeMaintainRecord(code);
    }

    @Override
    public Paginable<MaintainRecord> queryMaintainRecordPage(int start,
            int limit, MaintainRecord condition) {
        Paginable<MaintainRecord> page = maintainRecordBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (MaintainRecord maintainRecord : page.getList()) {
                initMaintainRecord(maintainRecord);
            }
        }
        return page;
    }

    @Override
    public List<MaintainRecord> queryMaintainRecordList(
            MaintainRecord condition) {
        return maintainRecordBO.queryMaintainRecordList(condition);
    }

    @Override
    public MaintainRecord getMaintainRecord(String code) {
        MaintainRecord maintainRecord = maintainRecordBO
            .getMaintainRecord(code);

        initMaintainRecord(maintainRecord);

        return maintainRecord;
    }

    private void initMaintainRecord(MaintainRecord maintainRecord) {
        // 养护方信息
        SYSUser maintainInfo = sysUserBO
            .getSYSUserUnCheck(maintainRecord.getMaintain());
        maintainRecord.setMaintainInfo(maintainInfo);
    }
}
