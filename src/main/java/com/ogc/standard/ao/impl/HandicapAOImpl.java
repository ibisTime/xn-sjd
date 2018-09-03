package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IHandicapAO;
import com.ogc.standard.bo.IHandicapBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Handicap;
import com.ogc.standard.domain.HandicapItem;
import com.ogc.standard.dto.res.XN650065Res;
import com.ogc.standard.enums.ESimuOrderDirection;

@Service
public class HandicapAOImpl implements IHandicapAO {

    @Autowired
    private IHandicapBO handicapBO;

    @Override
    public Paginable<Handicap> queryHandicapPage(int start, int limit,
            Handicap condition) {
        return handicapBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Handicap> queryHandicapList(Handicap condition) {
        return handicapBO.queryHandicapList(condition);
    }

    @Override
    public XN650065Res getHandicap(String symbol, String toSymbol) {
        XN650065Res data = new XN650065Res();

        // 买盘
        List<HandicapItem> bids = new ArrayList<>();

        // 查询买方
        List<Handicap> bidsHandicap = handicapBO.queryHandicapList(symbol,
            toSymbol, ESimuOrderDirection.BUY.getCode(), 5);
        for (Handicap handicap : bidsHandicap) {
            HandicapItem item = new HandicapItem();
            item.setPrice(handicap.getPrice());
            item.setCount(handicap.getCount());

            bids.add(item);
        }
        data.setBids(bids);

        // 卖盘
        List<HandicapItem> asks = new ArrayList<>();

        // 查询卖方
        List<Handicap> asksSimuOrder = handicapBO.queryHandicapList(symbol,
            toSymbol, ESimuOrderDirection.SELL.getCode(), 5);
        for (Handicap handicap : asksSimuOrder) {
            HandicapItem item = new HandicapItem();
            item.setPrice(handicap.getPrice());
            item.setCount(handicap.getCount());

            asks.add(item);
        }
        data.setAsks(asks);

        return data;
    }
}
