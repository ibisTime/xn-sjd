/**
 * @Title AwardMonthAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午3:10:18 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAwardMonthAO;
import com.ogc.standard.bo.IAwardMonthBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AwardMonth;
import com.ogc.standard.dto.res.XN802397Res;

/** 
 * @author: taojian 
 * @since: 2018年9月17日 下午3:10:18 
 * @history:
 */
@Service
public class AwardMonthAOImpl implements IAwardMonthAO {

    @Autowired
    private IAwardMonthBO awardMonthBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<AwardMonth> queryAwardMonthPage(int start, int limit,
            AwardMonth condition) {
        Paginable<AwardMonth> page = awardMonthBO.getPaginable(start, limit,
            condition);
        for (AwardMonth data : page.getList()) {
            data.setUser(userBO.getUser(data.getUserId()));
        }
        return page;
    }

    @Override
    public AwardMonth getAwardMonth(AwardMonth condition) {
        return null;
    }

    @Override
    public XN802397Res statistics(String userId) {

        return null;
    }

}
