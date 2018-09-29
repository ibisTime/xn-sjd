package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGroupAdoptOrderDAO;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class GroupAdoptOrderBOImpl extends PaginableBOImpl<GroupAdoptOrder>
        implements IGroupAdoptOrderBO {

    @Autowired
    private IGroupAdoptOrderDAO groupAdoptOrderDAO;

    @Override
    public boolean isGroupAdoptOrderExist(String code) {
        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setCode(code);
        if (groupAdoptOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveGroupAdoptOrder(GroupAdoptOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.GROUP_ADOPT_ORDER
                .getCode());
            data.setCode(code);
            groupAdoptOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public String saveGroupAdoptOrderFirst(GroupAdoptOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.GROUP_ADOPT_ORDER
                .getCode());
            data.setCode(code);
            groupAdoptOrderDAO.insertFirst(data);
        }
        return code;
    }

    @Override
    public String saveGroupAdoptOrderUnFirst(GroupAdoptOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.GROUP_ADOPT_ORDER
                .getCode());
            data.setCode(code);
            groupAdoptOrderDAO.insertUnFirst(data);
        }
        return code;
    }

    @Override
    public void refreshCancelGroupAdoptOrder(GroupAdoptOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            groupAdoptOrderDAO.updateCancelGroupAdoptOrder(data);
        }
    }

    @Override
    public void payGroupAdoptOrder(GroupAdoptOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            groupAdoptOrderDAO.updatePayGroupAdoptOrder(data);
        }
    }

    @Override
    public int removeGroupAdoptOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            GroupAdoptOrder data = new GroupAdoptOrder();
            data.setCode(code);
            count = groupAdoptOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshGroupAdoptOrder(GroupAdoptOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = groupAdoptOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition) {
        return groupAdoptOrderDAO.selectList(condition);
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrder(String code) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setCode(code);
            data = groupAdoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单编号不存在");
            }
        }
        return data;
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrderByIdentifyCode(String identifyCode) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(identifyCode)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setIdentifyCode(identifyCode);
            data = groupAdoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "识别码不存在");
            }
        }
        return data;
    }

}
