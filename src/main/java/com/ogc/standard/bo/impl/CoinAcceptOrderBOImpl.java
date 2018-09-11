package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICoinAcceptOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.ICoinAcceptOrderDAO;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECoin;
import com.ogc.standard.enums.ECoinAcceptOrderStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ETradeOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class CoinAcceptOrderBOImpl extends PaginableBOImpl<CoinAcceptOrder>
        implements ICoinAcceptOrderBO {

    @Autowired
    private ICoinAcceptOrderDAO coinAcceptOrderDAO;

    @Override
    public boolean isCoinAcceptOrderExist(String code) {
        CoinAcceptOrder condition = new CoinAcceptOrder();
        condition.setCode(code);
        if (coinAcceptOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveBuyAcceptOrder(XN625270Req req, String acceptUser) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ACCEPT_ORDER.getCode());

        CoinAcceptOrder data = new CoinAcceptOrder();
        data.setCode(code);
        data.setType(EBoolean.NO.getCode()); // 0买入/1卖出
        data.setUserId(req.getUserId());
        data.setAcceptUser(acceptUser);
        data.setTradeCurrency(req.getTradeCurrency());

        data.setTradeCoin(ECoin.X.getCode());
        data.setTradeCoin(req.getTradePrice());
        data.setCount(StringValidater.toBigDecimal(req.getCount()));
        data.setTradeAmount(StringValidater.toBigDecimal(req.getTradeAmount()));
        data.setFee(BigDecimal.ZERO);

        data.setInvalidDatetime(DateUtil.getRelativeDateOfMinute(new Date(),
            SysConstants.ACCEPT_ORDER_PAY_LIMIT));
        data.setPayType(req.getPayType());
        data.setPayBank(req.getPayBank());
        data.setPayCardNo(req.getPayCardNo());

        data.setStatus(ECoinAcceptOrderStatus.TO_PAY.getCode());
        data.setCreateDatetime(new Date());

        coinAcceptOrderDAO.insert(data);
        return code;
    }

    @Override
    public String saveSellAcceptOrder(XN625271Req req, String acceptUser) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int removeCoinAcceptOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CoinAcceptOrder data = new CoinAcceptOrder();
            data.setCode(code);
            count = coinAcceptOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCoinAcceptOrder(CoinAcceptOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            // count = coinAcceptOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<CoinAcceptOrder> queryCoinAcceptOrderList(
            CoinAcceptOrder condition) {
        return coinAcceptOrderDAO.selectList(condition);
    }

    @Override
    public CoinAcceptOrder getCoinAcceptOrder(String code) {
        CoinAcceptOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            CoinAcceptOrder condition = new CoinAcceptOrder();
            condition.setCode(code);
            data = coinAcceptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "keyName记录不存在");
            }
        }
        return data;
    }

    @Override
    public int cancel(CoinAcceptOrder acceptOrder, String updater,
            String remark) {
        int count = 0;
        if (acceptOrder != null) {
            acceptOrder.setStatus(ETradeOrderStatus.CANCEL.getCode());
            acceptOrder.setUpdater(updater);
            acceptOrder.setUpdateDatetime(new Date());
            acceptOrder
                .setRemark(StringUtils.isNotBlank(remark) ? remark : "订单已被取消");
            count = coinAcceptOrderDAO.updateCancel(acceptOrder);
        }
        return count;
    }

    @Override
    public int markPay(CoinAcceptOrder tradeOrder, String updater,
            String remark) {
        int count = 0;
        if (tradeOrder != null) {
            tradeOrder.setStatus(ETradeOrderStatus.PAYED.getCode());
            tradeOrder.setMarkDatetime(new Date());
            tradeOrder.setUpdater(updater);
            tradeOrder.setUpdateDatetime(new Date());
            tradeOrder.setRemark(
                StringUtils.isNotBlank(remark) ? remark : "已标记付款，待释放");
            count = coinAcceptOrderDAO.updateMarkPay(tradeOrder);
        }
        return count;
    }

}
