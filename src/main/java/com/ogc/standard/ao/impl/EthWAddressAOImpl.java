/**
 * @Title EthWAddressAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:43:34 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import com.ogc.standard.ao.IEthWAddressAO;
import com.ogc.standard.bo.IEthWAddressBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.EthWAddress;
import com.ogc.standard.enums.EWAddressStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:43:34 
 * @history:
 */
@Service
public class EthWAddressAOImpl implements IEthWAddressAO {

    @Autowired
    private IEthWAddressBO ethWAddressBO;

    @Override
    public void importWAddress(String address) {
        EthWAddress ethWAddress = ethWAddressBO
            .getEthWAddressByAddress(address);
        if (ethWAddress != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "归集地址" + address + "已经存在，请勿重复导入");
        }
        // 地址有效性校验
        if (!WalletUtils.isValidAddress(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址" + address + "不符合以太坊规则，请仔细核对");
        }
        ethWAddressBO.saveEthWAddress(address);
    }

    @Override
    public void abandon(Long id) {
        EthWAddress ethWAddress = ethWAddressBO.getEthWAddress(id);
        if (EWAddressStatus.INVALID.getCode().equals(ethWAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        ethWAddressBO.abandon(ethWAddress);
    }

    @Override
    public Paginable<EthWAddress> queryEthWAddressPage(int start, int limit,
            EthWAddress condition) {
        Paginable<EthWAddress> results = ethWAddressBO.getPaginable(start,
            limit, condition);
        return results;
    }

}
