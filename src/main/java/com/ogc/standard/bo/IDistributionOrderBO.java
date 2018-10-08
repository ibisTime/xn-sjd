/**
 * @Title IDistributionOrder.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author xieyj  
 * @date 2018年10月4日 下午11:13:29 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;

import com.ogc.standard.domain.AdoptOrder;

/** 
 * @author: xieyj 
 * @since: 2018年10月4日 下午11:13:29 
 * @history:
 */
public interface IDistributionOrderBO {

    BigDecimal distribution(AdoptOrder data);
}
