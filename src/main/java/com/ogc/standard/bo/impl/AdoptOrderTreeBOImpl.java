package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAdoptOrderTreeDAO;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class AdoptOrderTreeBOImpl extends PaginableBOImpl<AdoptOrderTree>
        implements IAdoptOrderTreeBO {

    @Autowired
    private IAdoptOrderTreeDAO adoptOrderTreeDAO;

    @Override
    public boolean isAdoptOrderTreeExist(String code) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setCode(code);
        if (adoptOrderTreeDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveAdoptOrderTree(AdoptOrderTree data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.ADOPT_ORDER_TREE
                .getCode());
            data.setCode(code);
            adoptOrderTreeDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeAdoptOrderTree(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrderTree data = new AdoptOrderTree();
            data.setCode(code);
            count = adoptOrderTreeDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshAdoptOrderTree(AdoptOrderTree data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = adoptOrderTreeDAO.update(data);
        }
        return count;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition) {
        return adoptOrderTreeDAO.selectList(condition);
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = null;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setCode(code);
            data = adoptOrderTreeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录编号不存在");
            }
        }
        return data;
    }

    @Override
    public void giveTree(AdoptOrderTree data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            adoptOrderTreeDAO.giveTree(data);
        }
    }
}
