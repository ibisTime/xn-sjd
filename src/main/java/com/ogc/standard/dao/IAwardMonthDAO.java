/**
 * @Title IAwardMonthDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午1:31:05 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AwardMonth;

/** 
 * @author: taojian 
 * @since: 2018年9月17日 下午1:31:05 
 * @history:
 */
public interface IAwardMonthDAO extends IBaseDAO<AwardMonth> {
    String NAMESPACE = IAwardMonthDAO.class.getName().concat(".");

    public int updateUnsettle(AwardMonth data);

    public int updateSettle(AwardMonth data);

}
