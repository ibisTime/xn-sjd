package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IDefaultPostageAO;
import com.ogc.standard.bo.IDefaultPostageBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DefaultPostage;
import com.ogc.standard.domain.SYSUser;

@Service
public class DefaultPostageAOImpl implements IDefaultPostageAO {

    @Autowired
    private IDefaultPostageBO defaultPostageBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public void editDefaultPostage(String code, BigDecimal price,
            String updater) {
        defaultPostageBO.refreshPrice(code, price, updater);
    }

    @Override
    public Paginable<DefaultPostage> queryDefaultPostagePage(int start,
            int limit, DefaultPostage condition) {
        Paginable<DefaultPostage> page = defaultPostageBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (DefaultPostage defaultPostage : page.getList()) {
                init(defaultPostage);
            }
        }

        return page;
    }

    @Override
    public List<DefaultPostage> queryDefaultPostageList(
            DefaultPostage condition) {
        List<DefaultPostage> list = defaultPostageBO
            .queryDefaultPostageList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (DefaultPostage defaultPostage : list) {
                init(defaultPostage);
            }
        }

        return list;

    }

    @Override
    public DefaultPostage getDefaultPostage(String code) {
        DefaultPostage defaultPostage = defaultPostageBO
            .getDefaultPostage(code);

        init(defaultPostage);

        return defaultPostage;
    }

    private void init(DefaultPostage defaultPostage) {
        SYSUser sysUser = sysUserBO
            .getSYSUserUnCheck(defaultPostage.getUpdater());

        String updaterName = null;
        if (null != sysUser) {
            updaterName = sysUser.getMobile();
            if (null != sysUser.getRealName()) {
                updaterName = sysUser.getRealName() + updaterName;
            }
        }
        defaultPostage.setUpdaterName(updaterName);
    }
}
