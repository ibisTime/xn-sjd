package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IApplyBindMaintainDAO;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class ApplyBindMaintainBOImpl extends PaginableBOImpl<ApplyBindMaintain>
        implements IApplyBindMaintainBO {

    @Autowired
    private IApplyBindMaintainDAO applyBindMaintainDAO;

    @Override
    public boolean isApplyBindMaintainExist(String code) {
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setCode(code);
        if (applyBindMaintainDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveApplyBindMaintain(ApplyBindMaintain data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.APPLY_BIND_MAINTAIN.getCode());
            data.setCode(code);
            applyBindMaintainDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeApplyBindMaintain(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ApplyBindMaintain data = new ApplyBindMaintain();
            data.setCode(code);
            count = applyBindMaintainDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshApplyBindMaintain(ApplyBindMaintain data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = applyBindMaintainDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition) {
        return applyBindMaintainDAO.selectList(condition);
    }

    @Override
    public ApplyBindMaintain getApplyBindMaintain(String code) {
        ApplyBindMaintain data = null;
        if (StringUtils.isNotBlank(code)) {
            ApplyBindMaintain condition = new ApplyBindMaintain();
            condition.setCode(code);
            data = applyBindMaintainDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录编号不存在");
            }
        }
        return data;
    }

    @Override
    public void approveApplyBindMaintain(ApplyBindMaintain data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            applyBindMaintainDAO.approveApplyBindMaintain(data);
        }
    }
}
