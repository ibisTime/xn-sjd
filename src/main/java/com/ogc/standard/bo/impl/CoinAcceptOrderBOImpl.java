package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICoinAcceptOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICoinAcceptOrderDAO;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.enums.EGeneratePrefix;
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
    public String saveCoinAcceptOrder(CoinAcceptOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.DH.getCode());
            data.setCode(code);
            coinAcceptOrderDAO.insert(data);
        }
        return code;
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
}
