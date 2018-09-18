/**
 * @Title IAwardMonthBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午1:44:09 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Award;
import com.ogc.standard.domain.AwardMonth;

/** 
 * @author: taojian 
 * @since: 2018年9月17日 下午1:44:09 
 * @history:
 */
public interface IAwardMonthBO extends IPaginableBO<AwardMonth> {
    public boolean isAwardMonthExist(String userId);

    public AwardMonth getAwardMonth(Long id);

    public AwardMonth getAwardMonth(AwardMonth condition);

    public int addAwardMonth(Award award, String remark);

    public void refreshAwardMonthSettle(Award data, String handleResult);

    public void addNewAwardMonth(String userId);

    public void refreshAwardMonthUnsettle(String userId, BigDecimal count);

    public List<AwardMonth> queryAwardMonthList(AwardMonth condition);

}
