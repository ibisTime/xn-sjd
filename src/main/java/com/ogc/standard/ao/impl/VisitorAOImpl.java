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
import com.ogc.standard.exception.BizException;

@Service
public class VisitorAOImpl implements IVisitorAO {

    @Autowired
    private IVisitorBO visitorBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addVisitor(Visitor data) {
        return visitorBO.saveVisitor(data);
    }

    @Override
    public int editVisitor(Visitor data) {
        if (!visitorBO.isVisitorExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return visitorBO.refreshVisitor(data);
    }

    @Override
    public int dropVisitor(String code) {
        if (!visitorBO.isVisitorExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return visitorBO.removeVisitor(code);
    }

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
