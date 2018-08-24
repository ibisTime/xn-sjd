package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAttentionBO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.bo.IGroupCoinBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGroupDAO;
import com.ogc.standard.domain.Attention;
import com.ogc.standard.domain.Group;
import com.ogc.standard.domain.GroupCoin;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECoin;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGroupStatus;
import com.ogc.standard.exception.BizException;

@Component
public class GroupBOImpl extends PaginableBOImpl<Group> implements IGroupBO {

    @Autowired
    private IGroupDAO groupDAO;

    @Autowired
    private IAttentionBO attentionBO;

    @Autowired
    private IGroupCoinBO groupCoinBO;

    @Override
    public String saveGroup(String matchCode, String teamCode, String userId,
            BigDecimal initAmount, String symbol) {
        String code = null;
        if (StringUtils.isNotBlank(matchCode)
                && StringUtils.isNotBlank(teamCode)
                && StringUtils.isNotBlank(userId) && null != initAmount) {

            code = OrderNoGenerater.generate(EGeneratePrefix.GROUP.getCode());

            Group group = new Group();
            group.setCode(code);
            group.setMatchCode(matchCode);
            group.setTeamCode(teamCode);
            group.setUserId(userId);
            group.setSymbol(ECoin.USDT.getCode());

            group.setInitAmount(initAmount);
            group.setBalance(initAmount);
            // 计算总资产
            group.setTotalAssets(initAmount);
            group.setTotalBenefit(BigDecimal.ZERO);
            group.setDayBenefit(BigDecimal.ZERO);

            group.setWeekBenefit(BigDecimal.ZERO);
            group.setMonthBenefit(BigDecimal.ZERO);
            group.setOrderNo(0);
            group.setFollowNumber(0);
            group.setStatus(EGroupStatus.START.getCode());

            group.setCreateDatetime(new Date());
            groupDAO.insert(group);

            // 初始化基础币种配置
            groupCoinBO.distributeAccount(userId, group.getCode(),
                group.getSymbol(), group.getInitAmount(),
                group.getTotalAssets(), 1.0);
        }
        return code;
    }

    @Override
    public void editGroupFollowNumber(String code, int followNumber) {
        Group data = new Group();
        data.setCode(code);
        data.setFollowNumber(followNumber);

        groupDAO.updateGroupFollowNumber(data);

    }

    @Override
    public int removeGroup(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Group data = new Group();
            data.setCode(code);
            count = groupDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<Group> queryGroupList(Group condition) {
        return groupDAO.selectList(condition);
    }

    @Override
    public Group getGroup(String code) {
        Group data = null;
        if (StringUtils.isNotBlank(code)) {
            Group condition = new Group();
            condition.setCode(code);
            data = groupDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "组合记录不存在");
            }
        }
        return data;
    }

    @Override
    public Group getGroupByVisitUserId(String code, String visitUserId) {
        Group data = null;
        if (StringUtils.isNotBlank(code)) {
            Group condition = new Group();
            condition.setCode(code);
            data = groupDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "组合记录不存在");
            }

            // 获取关注记录
            Attention attention = attentionBO.getAttention(visitUserId, code,
                EBoolean.YES.getCode()); // 1-关注
            data.setAttentionFlag(null == attention ? EBoolean.NO.getCode()
                    : EBoolean.YES.getCode());

            // 获取提醒记录
            Attention remind = attentionBO.getAttention(visitUserId, code,
                EBoolean.NO.getCode()); // 0-提醒
            data.setAttentionFlag(null == remind ? EBoolean.NO.getCode()
                    : EBoolean.YES.getCode());

            // 获取币种配置
            List<GroupCoin> groupCoins = groupCoinBO
                .queryGroupCoinListByGroupCode(code);
            data.setCoinList(groupCoins);

            // 获取最新成交记录

        }
        return data;
    }

}
