package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IDivideAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IDivideBO;
import com.ogc.standard.bo.IDivideDetailBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Divide;
import com.ogc.standard.domain.DivideDetail;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.ECoin;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;

@Service
public class DivideAOImpl implements IDivideAO {

    @Autowired
    private IDivideBO divideBO;

    @Autowired
    private IDivideDetailBO divideDetailBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public Paginable<Divide> queryDividePage(int start, int limit,
            Divide condition) {

        Paginable<Divide> page = divideBO.getPaginable(start, limit, condition);
        for (Divide divide : page.getList()) {
            divide.setUserInfo(userBO.getUser(divide.getDivideUser()));
        }

        return page;
    }

    @Override
    public void doDivide(String divideId, String divideProfit,
            String divideUser, String remark) {

        DivideDetail condition = new DivideDetail();
        condition.setDivideId(divideId);
        List<DivideDetail> divideDetails = divideDetailBO
            .queryDivideDetailList(condition);

        // 本次分红币的总个数
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DivideDetail divideDetail : divideDetails) {
            totalAmount = totalAmount.add(divideDetail.getAmount());
        }

        if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        // 个人分红额=可分配利润/币的总个数*持币个数
        BigDecimal totalDivideAmount = BigDecimal.ZERO;
        for (DivideDetail divideDetail : divideDetails) {
            BigDecimal divideAmount = StringValidater.toBigDecimal(divideProfit)
                .divide(totalAmount, 4, BigDecimal.ROUND_DOWN)
                .multiply(divideDetail.getAmount());

            // 本次分红总金额
            totalDivideAmount = totalDivideAmount.add(divideAmount);

            divideDetail.setDivideAmount(divideAmount);
            divideDetail.setDivideDatetime(new Date());
            divideDetailBO.refreshDivideDetail(divideDetail);

            // 从平台账户划转 分红 到用户账户
            accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                ECoin.X.getCode(), divideDetail.getUserId(), ECoin.X.getCode(),
                divideAmount, EJourBizTypeUser.AJ_DIVIDE.getCode(),
                EJourBizTypePlat.AJ_DIVIDE.getCode(),
                EJourBizTypeUser.AJ_DIVIDE.getValue(),
                EJourBizTypePlat.AJ_DIVIDE.getValue(),
                divideDetail.getId().toString());
        }

        divideBO.refreshDivide(divideId,
            StringValidater.toBigDecimal(divideProfit), totalDivideAmount,
            divideUser, remark);
    }

    // 遍历user落地分红快照
    public void divide() {

        String divideId = divideBO.saveDivide().getId();

        // 查询所有的C端用户
        List<User> users = userBO.queryUserList(new User());

        for (User user : users) {
            BigDecimal amount = accountBO
                .getAccountByUser(user.getUserId(), ECoin.X.getCode())
                .getAmount();

            divideDetailBO.saveDivideDetail(user.getUserId(), ECoin.X.getCode(),
                amount, divideId);
        }

    }
}
