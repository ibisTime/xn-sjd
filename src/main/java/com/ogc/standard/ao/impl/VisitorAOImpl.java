package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IVisitorAO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.Visitor;

@Service
public class VisitorAOImpl implements IVisitorAO {

    @Autowired
    private IVisitorBO visitorBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<Visitor> queryVisitorPage(int start, int limit,
            Visitor condition) {
        return visitorBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Visitor> queryVisitorList(Visitor condition) {
        return visitorBO.queryVisitorList(condition);
    }

    @Override
    public Visitor getVisitor(String code) {
        return visitorBO.getVisitor(code);
    }

    public void init(Visitor data) {
        if (StringUtils.isNotBlank(data.getUserId())) {
            User user = userBO.getUser(data.getUserId());
            data.setNickname(user.getNickname());
        }
    }
}
