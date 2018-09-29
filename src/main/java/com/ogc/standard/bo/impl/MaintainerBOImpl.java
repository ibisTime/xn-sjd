package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IMaintainerBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IMaintainerDAO;
import com.ogc.standard.domain.Maintainer;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class MaintainerBOImpl extends PaginableBOImpl<Maintainer>
        implements IMaintainerBO {

    @Autowired
    private IMaintainerDAO maintainerDAO;

    @Override
    public boolean isMaintainerExist(String code) {
        Maintainer condition = new Maintainer();
        condition.setCode(code);
        if (maintainerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveMaintainer(String maintainId, String name, String mobile,
            String updater, String remark) {
        Maintainer maintainer = new Maintainer();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Maintainer.getCode());
        maintainer.setCode(code);
        maintainer.setMaintainId(maintainId);
        maintainer.setName(name);
        maintainer.setMobile(mobile);

        maintainer.setUpdateDatetime(new Date());
        maintainer.setUpdater(updater);
        maintainer.setRemark(remark);
        maintainerDAO.insert(maintainer);
        return code;
    }

    @Override
    public void removeMaintainer(String code) {
        if (StringUtils.isNotBlank(code)) {
            Maintainer data = new Maintainer();
            data.setCode(code);
            maintainerDAO.delete(data);
        }
    }

    @Override
    public void refreshMaintainer(String code, String name, String mobile,
            String updater, String remark) {
        Maintainer maintainer = new Maintainer();

        maintainer.setCode(code);
        maintainer.setName(name);
        maintainer.setMobile(mobile);

        maintainer.setUpdateDatetime(new Date());
        maintainer.setUpdater(updater);
        maintainer.setRemark(remark);
        maintainerDAO.updateMaintainer(maintainer);
    }

    @Override
    public List<Maintainer> queryMaintainerList(Maintainer condition) {
        return maintainerDAO.selectList(condition);
    }

    @Override
    public Maintainer getMaintainer(String code) {
        Maintainer data = null;
        if (StringUtils.isNotBlank(code)) {
            Maintainer condition = new Maintainer();
            condition.setCode(code);
            data = maintainerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "养护人不存在！");
            }
        }
        return data;
    }

}
