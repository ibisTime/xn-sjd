package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.bo.ISimuMatchResultBO;
import com.ogc.standard.bo.ISimuMatchResultHistoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuMatchResultDAO;
import com.ogc.standard.domain.SimuMatchResult;
import com.ogc.standard.domain.SimuMatchResultHistory;
import com.ogc.standard.domain.SimuOrder;

@Component
public class SimuMatchResultBOImpl extends PaginableBOImpl<SimuMatchResult>
        implements ISimuMatchResultBO {

    @Autowired
    private ISimuMatchResultDAO simuMatchResultDAO;

    @Autowired
    private ISimuMatchResultHistoryBO simuMatchResultHistoryBO;

    public String saveSimuMatchResult(SimuMatchResult data) {
        String code = null;
        if (data != null) {
            simuMatchResultDAO.insert(data);
        }
        return code;
    }

    public int refreshSimuMatchResult(SimuMatchResult data) {
        int count = 0;
        count = simuMatchResultDAO.update(data);
        return count;
    }

    @Override
    public void doSimuMatchResult(SimuOrder bidsOrder, SimuOrder asksOrder) {
        SimuMatchResult data = null;
        if (null != bidsOrder && null != asksOrder) {

            // 根据买卖双方委托单编号搜索撮合结果，无则新增，有则更新
            SimuMatchResult condition = new SimuMatchResult();
            condition.setBuyOrderCode(bidsOrder.getCode());
            condition.setSellOrderCode(asksOrder.getCode());
            data = simuMatchResultDAO.select(condition);
            if (data == null) {

                data = new SimuMatchResult();
                data.setSymbol(bidsOrder.getSymbol());
                data.setToSymbol(bidsOrder.getToSymbol());
                data.setBuyOrderCode(bidsOrder.getCode());
                data.setSellOrderCode(asksOrder.getCode());
                data.setBuyUserId(bidsOrder.getUserId());

                data.setSellUserId(asksOrder.getUserId());
                // 交易数量
                data.setBuyAmount(bidsOrder.getTotalAmount());
                // 计价数量
                data.setSellAmount(asksOrder.getTotalAmount());
                data.setFee(bidsOrder.getTradedFee());
                data.setCreateDatetime(new Date());

                saveSimuMatchResult(data);

            } else {

                data.setBuyAmount(
                    bidsOrder.getTotalAmount().add(data.getBuyAmount()));
                data.setSellAmount(
                    asksOrder.getTotalAmount().add(data.getSellAmount()));
                data.setFee(bidsOrder.getTradedFee().add(data.getFee()));

                refreshSimuMatchResult(data);

            }
        }
    }

    @Transactional
    public void doCheckMatchResult() {

        // 获取所有的存活撮合结果
        List<SimuMatchResult> matchResults = simuMatchResultDAO
            .selectList(new SimuMatchResult());

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
            simuMatchResultDAO.delete(matchResult);
        }

    }

}
