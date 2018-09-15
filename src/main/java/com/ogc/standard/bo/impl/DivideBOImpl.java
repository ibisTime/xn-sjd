package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IDivideBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IDivideDAO;
import com.ogc.standard.domain.Divide;
import com.ogc.standard.enums.EDivideStatus;

@Component
public class DivideBOImpl extends PaginableBOImpl<Divide> implements IDivideBO {

    @Autowired
    private IDivideDAO divideDAO;

    @Override
    public void saveDivide() {
        Divide data = new Divide();
        data.setStatus(EDivideStatus.TO_DIVIDE.getCode());
        data.setCreateDatetime(new Date());
        divideDAO.insert(data);
    }

    @Override
    public void refreshDivide(String id, BigDecimal divideProfit,
            BigDecimal divideAmount, String divideUser, String remark) {

        Divide data = new Divide();
        data.setId(id);
        data.setDivideProfit(divideProfit);
        data.setDivideAmount(divideAmount);
        data.setStatus(EDivideStatus.DIVIDED.getCode());
        data.setDivideDatetime(new Date());
        data.setDivideUser(divideUser);
        data.setRemark(remark);

        divideDAO.update(data);
    }

    @Override
    public List<Divide> queryDivideList(Divide condition) {
        return divideDAO.selectList(condition);
    }

    // @Override
    // public Divide getDivide(String code) {
    // Divide data = null;
    // if (StringUtils.isNotBlank(code)) {
    // Divide condition = new Divide();
    // condition.setCode(code);
    // data = divideDAO.select(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "分红记录不存在");
    // }
    // }
    // return data;
    // }
}
