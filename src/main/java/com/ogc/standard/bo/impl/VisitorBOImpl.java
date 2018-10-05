package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IVisitorDAO;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class VisitorBOImpl extends PaginableBOImpl<Visitor>
        implements IVisitorBO {

    @Autowired
    private IVisitorDAO visitorDAO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String saveVisitor(String adoptTreeCode, String userId) {
        User user = userBO.getUser(userId);
        Visitor data = new Visitor();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.VISITOR.getCode());
        data.setCode(code);
        data.setAdoptTreeCode(adoptTreeCode);
        data.setUserId(userId);
        data.setPhoto(user.getPhoto());
        data.setCreateDatetime(new Date());

        visitorDAO.insert(data);
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
    public Visitor getTopTenVisitor(String adoptTreeCode, String userId) {
        Visitor data = null;
        if (StringUtils.isNotBlank(adoptTreeCode)
                && StringUtils.isNotBlank(userId)) {
            Visitor condition = new Visitor();
            condition.setAdoptTreeCode(adoptTreeCode);
            condition.setUserId(userId);
            condition.setOrder("create_datetime", "asc");

            List<Visitor> visitorList = visitorDAO.selectList(condition, 0, 10);
            if (CollectionUtils.isNotEmpty(visitorList)) {
                data = visitorList.get(0);
            }
        }
        return data;
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
