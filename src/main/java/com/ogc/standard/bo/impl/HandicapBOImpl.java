package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IHandicapBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IHandicapDAO;
import com.ogc.standard.domain.Handicap;
import com.ogc.standard.enums.ESimuOrderDirection;

@Component
public class HandicapBOImpl extends PaginableBOImpl<Handicap>
        implements IHandicapBO {

    @Autowired
    private IHandicapDAO handicapDAO;

    @Override
    public void saveHandicap(Handicap data) {
        if (data != null) {
            handicapDAO.insert(data);
        }
    }

    @Override
    public int removeHandicap(String orderCode) {
        int count = 0;
        if (StringUtils.isNotBlank(orderCode)) {
            Handicap data = new Handicap();
            data.setOrderCode(orderCode);
            count = handicapDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshHandicap(Handicap data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getId() + "")) {
            count = handicapDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Handicap> queryHandicapList(String symbol, String toSymbol,
            String direction, int handicapQuantity) {
        Handicap condition = new Handicap();
        condition.setDirection(direction);
        condition.setSymbol(symbol);
        condition.setToSymbol(toSymbol);

        if (ESimuOrderDirection.BUY.getCode().equals(direction)) {

            condition.setOrder("price desc");

        } else {

            condition.setOrder("price asc");

        }

        Paginable<Handicap> page = getPaginable(1, handicapQuantity, condition);

        return page.getList();
    }

    @Override
    public List<Handicap> queryHandicapList(Handicap condition) {
        return handicapDAO.selectList(condition);
    }

}
