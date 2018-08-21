package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.bo.IAttentionBO;
import com.ogc.standard.bo.IDayBenefitBO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.bo.base.Page;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Attention;
import com.ogc.standard.domain.Group;
import com.ogc.standard.enums.EGroupStatus;

@Service
public class GroupAOImpl implements IGroupAO {

    @Autowired
    private IGroupBO groupBO;

    @Autowired
    private IAttentionBO attentionBO;

    @Autowired
    private IDayBenefitBO dayBenefitBO;

    @Override
    public Paginable<Group> queryGroupPage(int start, int limit,
            Group condition) {
        return groupBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Group> queryGroupList(Group condition) {
        return groupBO.queryGroupList(condition);
    }

    @Override
    public Group getGroup(String code) {
        return groupBO.getGroup(code);
    }

    @Override
    public Group getGroupByVisitUserId(String code, String visitUserId) {
        return groupBO.getGroupByVisitUserId(code, visitUserId);
    }

    public Paginable<Group> queryMyAttentionGroupPage(int start, int pageSize,
            String userId) {

        Attention condition = new Attention();
        Paginable<Attention> page = attentionBO.getPaginable(start, pageSize,
            condition);

        Paginable<Group> pageResult = new Page<Group>(page.getPageNo(),
            page.getTotalCount());
        if (null != page) {

            List<Group> resultList = new ArrayList<Group>();

            for (Attention attention : page.getList()) {
                Group group = groupBO.getGroup(attention.getGroupCode());
                resultList.add(group);
            }

            pageResult.setList(resultList);
        }

        return pageResult;
    }

    public void scanProductToStart() {
        Group condition = new Group();

        // 获取状态为 进行中 的组合
        condition.setStatus(EGroupStatus.START.getCode());
        List<Group> groups = groupBO.queryGroupList(condition);
        for (Group group : groups) {

            // 计算昨日总资产
            BigDecimal yesterdayAssets = group.getTotalBenefit()
                .subtract(group.getDayBenefit());

            // 计算收益率
            BigDecimal benefitRate = yesterdayAssets
                .divide(group.getTotalBenefit(), 4, BigDecimal.ROUND_DOWN);

            dayBenefitBO.saveDayBenefit(group.getCode(), yesterdayAssets,
                group.getTotalBenefit(), group.getDayBenefit(),
                benefitRate.doubleValue());

        }

    }
}
