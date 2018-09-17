/**
 * @Title IAwardMonthBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午1:44:09 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AwardMonth;

/** 
 * @author: taojian 
 * @since: 2018年9月17日 下午1:44:09 
 * @history:
 */
@Component
public interface IAwardMonthBO extends IPaginableBO<AwardMonth> {

    public void addAwardMonth();

    public void refreshAwardMonth();
}
