package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IPresellInventoryDAO;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGroupType;
import com.ogc.standard.exception.BizException;

@Component
public class PresellInventoryBOImpl extends PaginableBOImpl<PresellInventory>
        implements IPresellInventoryBO {

    @Autowired
    private IPresellInventoryDAO presellInventoryDAO;

    @Override
    public String savePresellInventory(String groupCode, String treeNumber) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.PRESELL_INVENTORY.getCode());
        PresellInventory data = new PresellInventory();

        data.setCode(code);
        data.setGroupType(EGroupType.ORIGINAL_GROUP.getCode());
        data.setGroupCode(groupCode);
        data.setTreeNumber(treeNumber);
        presellInventoryDAO.insert(data);
        return code;
    }

    @Override
    public void refreshGroup(String code, String groupType, String groupCode) {
        PresellInventory data = new PresellInventory();

        data.setCode(code);
        data.setGroupType(groupType);
        data.setGroupCode(groupCode);

        presellInventoryDAO.updateGroup(data);
    }

    @Override
    public List<PresellInventory> queryPresellInventoryListByGroup(
            String groupCode) {
        PresellInventory condition = new PresellInventory();
        condition.setGroupCode(groupCode);
        return presellInventoryDAO.selectList(condition);
    }

    @Override
    public List<PresellInventory> queryTreeNumberListByGroup(String groupCode) {
        PresellInventory condition = new PresellInventory();
        condition.setGroupCode(groupCode);
        return presellInventoryDAO.selectTreeNumberList(condition);
    }

    @Override
    public List<PresellInventory> queryPresellInventoryList(
            PresellInventory condition) {
        return presellInventoryDAO.selectList(condition);
    }

    @Override
    public PresellInventory getPresellInventory(String code) {
        PresellInventory data = null;
        if (StringUtils.isNotBlank(code)) {
            PresellInventory condition = new PresellInventory();
            condition.setCode(code);
            data = presellInventoryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售权不存在");
            }
        }
        return data;
    }

    @Override
    public PresellInventory getTreeNumberByGroup(String groupCode) {
        PresellInventory data = null;
        if (StringUtils.isNotBlank(groupCode)) {
            PresellInventory condition = new PresellInventory();
            condition.setGroupCode(groupCode);
            data = presellInventoryDAO.selectTreeNumber(condition);
        }
        return data;
    }

}
