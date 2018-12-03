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

import com.ogc.standard.dto.res.XN629048Res;

/** 
 * @author: xieyj 
 * @since: 2018年10月4日 下午11:13:29 
 * @history:
 */
public interface IDistributionOrderBO {

    // 是否抵扣
    public XN629048Res getAdoptOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk);

    // 是否抵扣
    public XN629048Res getPresellOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk);

    // 是否抵扣
    public XN629048Res getCommodityOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk);

    // 是否抵扣
    public XN629048Res getCommodityGroupOrderDeductAmount(String payGroup,
            String isDk);

    // 认养分销
    BigDecimal adoptDistribution(String code, String ownerId, BigDecimal amount,
            String applyUser, String type, XN629048Res resultRes);

    // 预售分销
    BigDecimal presellDistribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes);

    // 商城分销
    BigDecimal commodityDistribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes);
}
