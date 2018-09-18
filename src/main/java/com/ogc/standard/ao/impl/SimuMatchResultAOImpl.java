package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISimuMatchResultAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISimuMatchResultBO;
import com.ogc.standard.bo.ISimuMatchResultHistoryBO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.SimuMatchResult;
import com.ogc.standard.domain.SimuMatchResultHistory;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;

@Service
public class SimuMatchResultAOImpl implements ISimuMatchResultAO {

    @Autowired
    private ISimuMatchResultBO simuMatchResultBO;

    @Autowired
    private ISimuMatchResultHistoryBO simuMatchResultHistoryBO;

    @Autowired
    private IAccountBO accountBO;

    @Transactional
    public void doCheckMatchResult() {

        // 获取所有的存活撮合结果
        List<SimuMatchResult> matchResults = simuMatchResultBO
            .querySimuMatchResultHistoryList(new SimuMatchResult());

        // 根据撮合结果 改变 账户
        for (SimuMatchResult matchResult : matchResults) {

            // 放入历史撮合结果
            SimuMatchResultHistory data = new SimuMatchResultHistory();
            data.setId(matchResult.getId());
            data.setBuyOrderCode(matchResult.getBuyOrderCode());
            data.setSellOrderCode(matchResult.getSellOrderCode());
            data.setBuyOrderDetailCode(matchResult.getBuyOrderDetailCode());
            data.setSellOrderDetailCode(matchResult.getSellOrderDetailCode());
            data.setSymbol(matchResult.getSymbol());
            data.setToSymbol(matchResult.getToSymbol());

            data.setBuyUserId(matchResult.getBuyUserId());
            data.setSellUserId(matchResult.getSellUserId());
            data.setBuyAmount(matchResult.getBuyAmount());
            data.setSellAmount(matchResult.getSellAmount());
            data.setBuyFee(matchResult.getBuyFee());
            data.setSellFee(matchResult.getSellFee());

            data.setCreateDatetime(matchResult.getCreateDatetime());
            simuMatchResultHistoryBO.saveSimuMatchResultHistory(data);

            // 删除存活撮合结果
            simuMatchResultBO.removeSimuMatchResult(matchResult.getId());

            // 解冻买家账户交易金额
            Account buyAccount = accountBO.getAccountByUser(data.getBuyUserId(),
                data.getToSymbol());
            buyAccount = accountBO.unfrozenAmount(buyAccount,
                data.getSellAmount(),
                EJourBizTypeUser.AJ_BBORDER_UNFROZEN_TRADE.getCode(),
                EJourBizTypeUser.AJ_BBORDER_UNFROZEN_TRADE.getValue(),
                data.getId().toString());

            // 解冻卖家账户交易金额
            Account sellAccount = accountBO
                .getAccountByUser(data.getSellUserId(), data.getSymbol());
            sellAccount = accountBO.unfrozenAmount(sellAccount,
                data.getBuyAmount(),
                EJourBizTypeUser.AJ_BBORDER_UNFROZEN_TRADE.getCode(),
                EJourBizTypeUser.AJ_BBORDER_UNFROZEN_TRADE.getValue(),
                data.getId().toString());

            // 划转交易币种，从 卖方 划给 买方
            accountBO.transAmount(data.getSellUserId(), data.getSymbol(),
                data.getBuyUserId(), data.getSymbol(), data.getSellAmount(),
                EJourBizTypeUser.AJ_BBORDER_SELL.getCode(),
                EJourBizTypeUser.AJ_BBORDER_BUY.getCode(),
                EJourBizTypeUser.AJ_BBORDER_SELL.getValue(),
                EJourBizTypeUser.AJ_BBORDER_BUY.getValue(),
                data.getId().toString());

            // 扣减买家手续费
            accountBO.transAmount(data.getBuyUserId(), data.getSymbol(),
                ESysUser.SYS_USER.getCode(), data.getSymbol(),
                data.getSellFee(), EJourBizTypeUser.AJ_BBORDER_FEE.getCode(),
                EJourBizTypePlat.AJ_BBORDER_FEE.getCode(),
                EJourBizTypeUser.AJ_BBORDER_FEE.getValue(),
                EJourBizTypePlat.AJ_BBORDER_FEE.getValue(),
                data.getId().toString());

            // 划转计价币种，从 买方 划给 卖方
            accountBO.transAmount(data.getBuyUserId(), data.getToSymbol(),
                data.getSellUserId(), data.getToSymbol(), data.getSellAmount(),
                EJourBizTypeUser.AJ_BBORDER_SELL.getCode(),
                EJourBizTypeUser.AJ_BBORDER_BUY.getCode(),
                EJourBizTypeUser.AJ_BBORDER_SELL.getValue(),
                EJourBizTypeUser.AJ_BBORDER_BUY.getValue(),
                data.getId().toString());

            // 扣减卖家手续费
            accountBO.transAmount(data.getSellUserId(), data.getToSymbol(),
                ESysUser.SYS_USER.getCode(), data.getToSymbol(),
                data.getSellFee(), EJourBizTypeUser.AJ_BBORDER_FEE.getCode(),
                EJourBizTypePlat.AJ_BBORDER_FEE.getCode(),
                EJourBizTypeUser.AJ_BBORDER_FEE.getValue(),
                EJourBizTypePlat.AJ_BBORDER_FEE.getValue(),
                data.getId().toString());
        }

    }

}
