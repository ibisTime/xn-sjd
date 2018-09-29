package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMaintainerAO;
import com.ogc.standard.bo.IMaintainerBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Maintainer;
import com.ogc.standard.exception.BizException;

@Service
public class MaintainerAOImpl implements IMaintainerAO {

    @Autowired
    private IMaintainerBO maintainerBO;

    @Override
    public String addMaintainer(String maintainId, String name, String mobile,
            String updater, String remark) {

        return maintainerBO.saveMaintainer(maintainId, name, mobile, updater,
            remark);

    }

    @Override
    public void dropMaintainer(String code) {
        if (!maintainerBO.isMaintainerExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        maintainerBO.removeMaintainer(code);
    }

    @Override
    public void editMaintainer(String code, String name, String mobile,
            String updater, String remark) {

        maintainerBO.refreshMaintainer(code, name, mobile, updater, remark);

    }

    @Override
    public Paginable<Maintainer> queryMaintainerPage(int start, int limit,
            Maintainer condition) {
        return maintainerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Maintainer> queryMaintainerList(Maintainer condition) {
        return maintainerBO.queryMaintainerList(condition);
    }

    @Override
    public Maintainer getMaintainer(String code) {
        return maintainerBO.getMaintainer(code);
    }

}
