package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAdoptOrderTreeDAO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class AdoptOrderTreeBOImpl extends PaginableBOImpl<AdoptOrderTree>
        implements IAdoptOrderTreeBO {

    @Autowired
    private IAdoptOrderTreeDAO adoptOrderTreeDAO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String saveAdoptOrderTree(Product product, AdoptOrder adoptOrder,
            String treeNumber) {
        AdoptOrderTree data = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        data.setCode(code);
        data.setOrderCode(adoptOrder.getCode());
        data.setParentCategoryCode(product.getParentCategoryCode());
        data.setCategoryCode(product.getCategoryCode());
        data.setProductCode(product.getCode());
        data.setTreeNumber(treeNumber);

        data.setStartDatetime(adoptOrder.getStartDatetime());
        data.setEndDatetime(adoptOrder.getEndDatetime());
        data.setAmount(adoptOrder.getPrice());
        if (EAdoptOrderStatus.TO_ADOPT.getCode()
            .equals(adoptOrder.getStatus())) {
            data.setStatus(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        }

        Company company = companyBO.getCompanyByUserId(product.getOwnerId());
        data.setCertificateTemplate(company.getCertificateTemplate());
        data.setCurrentHolder(adoptOrder.getApplyUser());
        adoptOrderTreeDAO.insert(data);
        return code;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition) {
        return adoptOrderTreeDAO.selectList(condition);
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(String orderCode) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOrderCode(orderCode);
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
                throw new BizException("xn0000", "认养权不存在");
            }
        }
        return data;
    }

    @Override
    public void giveTree(AdoptOrderTree data, User user, User toUser) {
        // 终止现有的认养权
        data.setStatus(EAdoptOrderTreeStatus.PRESENT.getCode());
        data.setRemark("赠送给" + toUser.getMobile());
        adoptOrderTreeDAO.updateStatus(data);

        // 产生新的认养权
        AdoptOrderTree newData = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        newData.setCode(code);
        newData.setOrderCode(data.getCode());
        newData.setCategoryCode(data.getCategoryCode());
        newData.setProductCode(data.getProductCode());
        newData.setTreeNumber(data.getTreeNumber());

        newData.setStartDatetime(new Date());
        newData.setEndDatetime(data.getEndDatetime());
        newData.setAmount(data.getAmount());
        newData.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        newData.setCurrentHolder(toUser.getUserId());
        newData.setRemark(user.getMobile() + "赠送");

        adoptOrderTreeDAO.insert(newData);
    }

    @Override
    public void refreshAdoptOrderTree(AdoptOrderTree data,
            EAdoptOrderTreeStatus adoptOrderTreeStatus) {
        if (data != null) {
            data.setStatus(adoptOrderTreeStatus.getCode());
            adoptOrderTreeDAO.updateStatus(data);
        }
    }

    @Override
    public long getCountByCurrentHolder(String currentHolder) {
        long count = 0;

        if (StringUtils.isNotBlank(currentHolder)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setCurrentHolder(currentHolder);
            count = adoptOrderTreeDAO.selectTotalCount(condition);
        }

        return count;
    }
}
