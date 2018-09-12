/**
 * @Title BtcMAddress.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午3:23:56 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBtcMAddressAO;
import com.ogc.standard.bo.IBtcMAddressBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BtcMAddress;
import com.ogc.standard.enums.EMAddressStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午3:23:56 
 * @history:
 */
@Service
public class BtcMAddressAOImpl implements IBtcMAddressAO {

    @Autowired
    private IBtcMAddressBO btcMAddressBO;

    @Override
    public String addAddress() {
        return btcMAddressBO.saveAddress();
    }

    @Override
    public void abandon(Long id) {
        BtcMAddress btcMAddress = btcMAddressBO.getAddressById(id);
        if (btcMAddress == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不存在改地址");
        }
        if (EMAddressStatus.INVALID.getCode().equals(btcMAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        btcMAddressBO.refreshStatus(id);

    }

    @Override
    public Paginable<BtcMAddress> queryBtcMAddressPage(int start, int limit,
            BtcMAddress condition) {
        return btcMAddressBO.getPaginable(start, limit, condition);
    }

}
