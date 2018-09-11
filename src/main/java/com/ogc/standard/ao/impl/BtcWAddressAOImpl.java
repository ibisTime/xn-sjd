/**
 * @Title BtcWAddressAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:20:56 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBtcWAddressAO;
import com.ogc.standard.bo.IBtcWAddressBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.BtcClient;
import com.ogc.standard.domain.BtcWAddress;
import com.ogc.standard.enums.EWAddressStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午8:20:56 
 * @history:
 */
@Service
public class BtcWAddressAOImpl implements IBtcWAddressAO {
    @Autowired
    private IBtcWAddressBO btcWAddressBO;

    @Override
    public void importWAddress(String address) {
        BtcWAddress btcWAddress = btcWAddressBO
            .getBtcWAddressByAddress(address);
        if (btcWAddress != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "归集地址" + address + "已经存在，请勿重复导入");
        }
        // 地址有效性校验
        if (!BtcClient.verifyAddress(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址" + address + "不符合比特币规则，请仔细核对");
        }
        btcWAddressBO.saveBtcWAddress(address);
    }

    @Override
    public void abandon(Long id) {
        BtcWAddress btcWAddress = btcWAddressBO.getBtcWAddress(id);
        if (btcWAddress == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "没有该用户");
        }
        if (EWAddressStatus.INVALID.getCode().equals(btcWAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        btcWAddressBO.abandon(btcWAddress);
    }

    @Override
    public Paginable<BtcWAddress> queryBtcWAddressPage(int start, int limit,
            BtcWAddress condition) {
        return btcWAddressBO.getPaginable(start, limit, condition);
    }

}
