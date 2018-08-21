package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGroupCoinBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IGroupCoinDAO;
import com.ogc.standard.domain.GroupCoin;
import com.ogc.standard.exception.BizException;

@Component
public class GroupCoinBOImpl extends PaginableBOImpl<GroupCoin>
        implements IGroupCoinBO {

    @Autowired
    private IGroupCoinDAO groupCoinDAO;

    @Override
    public int saveGroupCoin(GroupCoin data) {
        int count = 1;
        if (data != null) {
            groupCoinDAO.insert(data);
        }
        return count;
    }

    @Override
    public void removeGroupCoin(String groupCode, String symbol) {

        GroupCoin data = this.getGroupCoin(groupCode, symbol);
        groupCoinDAO.delete(data);

    }

    @Override
    public int refreshGroupCoin(String groupCode, String symbol) {
        int count = 0;

        GroupCoin data = this.getGroupCoin(groupCode, symbol);

        // count = groupCoinDAO.update(data);
        return count;
    }

    @Override
    public List<GroupCoin> queryGroupCoinListByGroupCode(String groupCode) {

        GroupCoin condition = new GroupCoin();
        condition.setGroupCode(groupCode);
        return groupCoinDAO.selectList(condition);

    }

    @Override
    public GroupCoin getGroupCoin(String groupCode, String symbol) {
        GroupCoin data = null;
        if (StringUtils.isNotBlank(groupCode)
                && StringUtils.isNotBlank(symbol)) {

            GroupCoin condition = new GroupCoin();
            condition.setGroupCode(groupCode);
            data = groupCoinDAO.select(condition);

            if (data == null) {
                throw new BizException("xn0000", "币种配置记录不存在");
            }

        }
        return data;
    }
}
