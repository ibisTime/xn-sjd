package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAdoptOrderDAO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class AdoptOrderBOImpl extends PaginableBOImpl<AdoptOrder> implements
        IAdoptOrderBO {

    @Autowired
    private IAdoptOrderDAO adoptOrderDAO;

    @Override
    public boolean isAdoptOrderExist(String code) {
        AdoptOrder condition = new AdoptOrder();
        condition.setCode(code);
        if (adoptOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveAdoptOrder(AdoptOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.ADOPT_ORDER
                .getCode());
            data.setCode(code);
            adoptOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeAdoptOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrder data = new AdoptOrder();
            data.setCode(code);
            count = adoptOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshAdoptOrder(AdoptOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = adoptOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition) {
        return adoptOrderDAO.selectList(condition);
    }

    @Override
    public AdoptOrder getAdoptOrder(String code) {
        AdoptOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrder condition = new AdoptOrder();
            condition.setCode(code);
            data = adoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
