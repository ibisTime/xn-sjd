/**
 * @Title DepositBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午3:03:52 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IDepositBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IDepositDAO;
import com.ogc.standard.domain.Deposit;
import com.ogc.standard.exception.BizException;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午3:03:52 
 * @history:
 */
@Component
public class DepositBOImpl extends PaginableBOImpl<Deposit>
        implements IDepositBO {

    @Autowired
    private IDepositDAO depositDAO;

    @Override
    public Deposit getDeposit(String code) {
        Deposit data = null;
        if (StringUtils.isNotBlank(code)) {
            Deposit condition = new Deposit();
            condition.setCode(code);
            data = depositDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "定存订单不存在");

            }
        }
        return data;
    }

}
