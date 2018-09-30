package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IVisitorDAO;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class VisitorBOImpl extends PaginableBOImpl<Visitor> implements
        IVisitorBO {

    @Autowired
    private IVisitorDAO visitorDAO;

    @Override
    public boolean isVisitorExist(String code) {
        Visitor condition = new Visitor();
        condition.setCode(code);
        if (visitorDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveVisitor(Visitor data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.VISITOR.getCode());
            data.setCode(code);
            visitorDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeVisitor(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Visitor data = new Visitor();
            data.setCode(code);
            count = visitorDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshVisitor(Visitor data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = visitorDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Visitor> queryVisitorList(Visitor condition) {
        return visitorDAO.selectList(condition);
    }

    @Override
    public Visitor getVisitor(String code) {
        Visitor data = null;
        if (StringUtils.isNotBlank(code)) {
            Visitor condition = new Visitor();
            condition.setCode(code);
            data = visitorDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "来访记录不存在");
            }
        }
        return data;
    }
}
