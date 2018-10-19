package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMaintainerAO;
import com.ogc.standard.bo.IMaintainerBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Maintainer;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.exception.BizException;

@Service
public class MaintainerAOImpl implements IMaintainerAO {

    @Autowired
    private IMaintainerBO maintainerBO;

    @Autowired
    private ISYSUserBO sysUserBO;

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
        Paginable<Maintainer> page = maintainerBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Maintainer maintainer : page.getList()) {

                init(maintainer);

            }
        }

        return page;
    }

    @Override
    public List<Maintainer> queryMaintainerList(Maintainer condition) {
        return maintainerBO.queryMaintainerList(condition);
    }

    @Override
    public Maintainer getMaintainer(String code) {
        Maintainer maintainer = maintainerBO.getMaintainer(code);

        init(maintainer);

        return maintainer;
    }

    private void init(Maintainer maintainer) {
        // 养护方信息
        SYSUser maintainInfo = sysUserBO
            .getSYSUserUnCheck(maintainer.getMaintainId());
        maintainer.setMaintainInfo(maintainInfo);

        // 更新人信息
        String updaterName = null;
        SYSUser updater = sysUserBO.getSYSUserUnCheck(maintainer.getUpdater());
        if (null != updater) {
            updaterName = updater.getMobile();
            if (StringUtils.isNotBlank(updater.getRealName())) {
                updaterName = updater.getRealName().concat("-")
                    .concat(updaterName);
            }
        }
        maintainer.setUpdaterName(updaterName);
    }
}
