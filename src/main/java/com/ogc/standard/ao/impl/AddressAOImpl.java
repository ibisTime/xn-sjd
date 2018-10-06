/**
 * @Title AddressAOImpl.java 
 * @Package com.xnjr.cpzc.user.impl 
 * @Description 
 * @author xieyj  
 * @date 2015年8月27日 上午10:41:50 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAddressAO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2015年8月27日 上午10:41:50 
 * @history:
 */
@Service
public class AddressAOImpl implements IAddressAO {
    @Autowired
    IUserBO userBO;

    @Autowired
    IAddressBO addressBO;

    @Override
    @Transactional
    public String addAddress(Address data) {
        User user = userBO.getUser(data.getUserId());
        String code = null;
        if (data != null) {
            // 用户没有地址，则第一条地址为默认地址
            if (!addressBO.isHaveAddress(data.getUserId())) {
                data.setIsDefault(EBoolean.YES.getCode());
            } else {
                // 如果新增地址设置为默认地址，该用户其他地址设置为非默认
                if (EBoolean.YES.getCode().equals(data.getIsDefault())) {
                    addressBO.refreshAddressDefByUser(data.getUserId(),
                        EBoolean.NO.getCode());
                }
            }
            code = addressBO.saveAddress(data);
        }
        return code;
    }

    @Override
    @Transactional
    public int editAddress(Address data) {
        int count = 0;
        if (data != null) {
            // 如果修改地址设置为默认地址，该用户其他地址设置为非默认
            if (EBoolean.YES.getCode().equals(data.getIsDefault())) {
                Address address = addressBO.getAddress(data.getCode());
                addressBO.refreshAddressDefByUser(address.getUserId(),
                    EBoolean.NO.getCode());
            }
            count = addressBO.refreshAddress(data);
        }
        return count;
    }

    @Override
    public int refreshAddressDef(String userId, String code, String isDefault) {
        if (StringUtils.isBlank(userId)) {
            throw new BizException("ZC000001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(code)) {
            throw new BizException("ZC000001", "收件地址编号不能为空");
        }
        if (StringUtils.isBlank(isDefault)) {
            throw new BizException("ZC000001", "地址默认值不能为空");
        }
        // 将原先的默认地址设置为非默认
        Address condition = new Address();
        condition.setUserId(userId);
        int count = addressBO.refreshAddressDefByUser(userId, isDefault);
        count = addressBO.refreshAddressDef(code, isDefault);
        return count;
    }

    @Override
    @Transactional
    public int setDefaultAddress(String code) {
        int count = 0;
        Address condition = new Address();
        condition.setCode(code);
        if (addressBO.getTotalCount(condition) > 0) {
            Address address = addressBO.getAddress(code);
            if (EBoolean.YES.getCode().equals(address.getIsDefault())) {
                throw new BizException("ZC000001", "该地址已经是该用户的默认地址，不需要重复设置");
            }
            // 将该用户所有地址设置为非默认
            addressBO.refreshAddressDefByUser(address.getUserId(),
                EBoolean.NO.getCode());
            // 将该地址设置为默认地址
            count = addressBO.refreshAddressDef(code, EBoolean.YES.getCode());
        } else {
            throw new BizException("ZC000001", "找不到该用户的地址，请核对用户ID和地址编号");
        }
        return count;
    }

    @Override
    public int dropAddress(String code) {
        return addressBO.deleteAddress(code);
    }

    @Override
    public List<Address> queryAddressList(Address condition) {
        return addressBO.queryAddressList(condition);
    }

    /** 
     * @see com.xnjr.mall.ao.IAddressAO#getAddress(java.lang.String)
     */
    @Override
    public Address getAddress(String code) {
        return addressBO.getAddress(code);
    }
}
