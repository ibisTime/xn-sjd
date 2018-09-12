package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISimuMatchResultAO;
import com.ogc.standard.bo.ISimuMatchResultBO;
import com.ogc.standard.bo.ISimuMatchResultHistoryBO;
import com.ogc.standard.domain.SimuMatchResult;
import com.ogc.standard.domain.SimuMatchResultHistory;

@Service
public class SimuMatchResultAOImpl implements ISimuMatchResultAO {

    @Autowired
    private ISimuMatchResultBO simuMatchResultBO;

    @Autowired
    private ISimuMatchResultHistoryBO simuMatchResultHistoryBO;

    @Transactional
    public void doCheckMatchResult() {

        // 获取所有的存活撮合结果
        List<SimuMatchResult> matchResults = simuMatchResultBO
            .querySimuMatchResultHistoryList(new SimuMatchResult());

        // 根据撮合结果 改变 账户
        for (SimuMatchResult matchResult : matchResults) {

            // 买家
            // GroupCoin gcAccount =
            // groupCoinBO.getGroupCoin(simuOrder.getGroupCode(),
            // simuOrder.getUserId(), simuOrder.getToSymbol());
            //
            // // 解冻金额
            // gcAccount = groupCoinBO.unfrozenAmount(gcAccount,
            // simuOrder.getTotalCount(),
            // EJourBizType.BUY_ORDER_UNFROZEN.getCode(),
            // EJourBizType.BUY_ORDER_UNFROZEN.getValue(), code);
            //
            // // 扣减
            // groupCoinBO.changeAmount(gcAccount,
            // simuOrder.getTotalCount().negate(),
            // simuOrder.getCode(), EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            // EJourBizType.BUY_ORDER_SUCCESS.getValue());
            //
            // // 添加购买的交易币种金额
            // GroupCoin symbolAccount = groupCoinBO.checkAccountAndDistribute(
            // simuOrder.getUserId(), gcAccount.getGroupCode(),
            // simuOrder.getSymbol());
            // symbolAccount = groupCoinBO.changeAmount(symbolAccount,
            // simuOrder.getTotalCount(), simuOrder.getCode(),
            // EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            // EJourBizType.BUY_ORDER_SUCCESS.getValue());

            // 卖家
            // GroupCoin gcAccount =
            // groupCoinBO.getGroupCoin(simuOrder.getGroupCode(),
            // simuOrder.getUserId(), simuOrder.getToSymbol());
            //
            // // 解冻金额
            // gcAccount = groupCoinBO.unfrozenAmount(gcAccount,
            // simuOrder.getTotalCount(),
            // EJourBizType.SELL_ORDER_UNFROZEN.getCode(),
            // EJourBizType.SELL_ORDER_UNFROZEN.getValue(), code);
            //
            // // 扣减币种金额
            // groupCoinBO.changeAmount(gcAccount,
            // simuOrder.getTotalCount().negate(),
            // simuOrder.getCode(), EJourBizType.SELL_ORDER_SUCCESS.getCode(),
            // EJourBizType.SELL_ORDER_SUCCESS.getValue());
            //
            // // 增加计价币种金额
            // GroupCoin symbolAccount = groupCoinBO.checkAccountAndDistribute(
            // simuOrder.getUserId(), simuOrder.getExchange(),
            // simuOrder.getToSymbol());
            // symbolAccount = groupCoinBO.changeAmount(symbolAccount,
            // simuOrder.getTotalCount(), simuOrder.getCode(),
            // EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            // EJourBizType.BUY_ORDER_SUCCESS.getValue());

            // 放入历史撮合结果
            SimuMatchResultHistory data = new SimuMatchResultHistory();
            data.setId(matchResult.getId());
            data.setBuyOrderCode(matchResult.getBuyOrderCode());
            data.setSellOrderCode(matchResult.getSellOrderCode());
            data.setSymbol(matchResult.getSymbol());
            data.setToSymbol(matchResult.getToSymbol());

            data.setBuyUserId(matchResult.getBuyUserId());
            data.setSellUserId(matchResult.getSellUserId());
            data.setBuyAmount(matchResult.getBuyAmount());
            data.setSellAmount(matchResult.getSellAmount());
            data.setFee(matchResult.getFee());

            data.setCreateDatetime(matchResult.getCreateDatetime());
            simuMatchResultHistoryBO.saveSimuMatchResultHistory(data);

            // 删除存活撮合结果
            simuMatchResultBO.removeSimuMatchResult(matchResult.getId());
        }

    }

}
