package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IHandicapAO;
import com.ogc.standard.bo.IHandicapBO;
import com.ogc.standard.domain.Handicap;
import com.ogc.standard.domain.HandicapGrade;
import com.ogc.standard.domain.HandicapItem;
import com.ogc.standard.dto.res.XN650065Res;
import com.ogc.standard.enums.ESimuOrderDirection;

@Service
public class HandicapAOImpl implements IHandicapAO {

    @Autowired
    private IHandicapBO handicapBO;

    @Override
    public XN650065Res getHandicap(String symbol, String toSymbol) {
        XN650065Res data = new XN650065Res();

        data.setBids(formatHandicap(symbol, toSymbol,
            ESimuOrderDirection.BUY.getCode()));

        data.setAsks(formatHandicap(symbol, toSymbol,
            ESimuOrderDirection.SELL.getCode()));

        return data;
    }

    private List<HandicapItem> formatHandicap(String symbol, String toSymbol,
            String direction) {

        List<HandicapItem> handicapItems = new ArrayList<>();

        List<HandicapGrade> asksGrades = handicapBO.queryHandicapList(symbol,
            toSymbol, direction);

        for (HandicapGrade handicapGrade : asksGrades) {
            HandicapItem item = new HandicapItem();
            item.setPrice(handicapGrade.getPrice());

            BigDecimal count = BigDecimal.ZERO;
            for (Handicap handicaps : handicapGrade.getHandicapList()) {
                count = count.add(handicaps.getCount());
            }
            item.setCount(count);

            handicapItems.add(item);
        }

        return handicapItems;

    }
}
