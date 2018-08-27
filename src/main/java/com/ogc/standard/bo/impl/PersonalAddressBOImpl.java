package com.ogc.standard.bo.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPersonalAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IPersonalAddressDAO;
import com.ogc.standard.domain.PersonalAddress;

@Component
public class PersonalAddressBOImpl extends PaginableBOImpl<PersonalAddress>
        implements IPersonalAddressBO {

    @Autowired
    private IPersonalAddressDAO personalAddressDAO;

    @Override
    public String savePersonalAddress(String symbol, String address,
            String label, String userId, String status) {
        String code = null;
        Date now = new Date();
        PersonalAddress data = new PersonalAddress();
        data.setSymbol(symbol);
        data.setAddress(address);
        data.setUserId(userId);
        data.setLabel(label);
        data.setStatus(status);
        data.setCreateDatetime(now);
        data.setUpdater(userId);
        data.setUpdateDatetime(now);
        personalAddressDAO.insert(data);
        return code;
    }

    @Override
    public int removePersonalAddress(long id) {
        int count = 0;
        PersonalAddress data = new PersonalAddress();
        data.setId(id);
        count = personalAddressDAO.delete(data);
        return count;
    }

    // @Override
    // public int refreshWithdrawAddress(WithdrawAddress data) {
    // int count = 0;
    // if (StringUtils.isNotBlank(data.getCode())) {
    // // count = withdrawAddressDAO.update(data);
    // }
    // return count;
    // }
    //
    // @Override
    // public List<WithdrawAddress> queryWithdrawAddressList(
    // WithdrawAddress condition) {
    // return withdrawAddressDAO.selectList(condition);
    // }
    //
    // @Override
    // public WithdrawAddress getWithdrawAddress(String code) {
    // WithdrawAddress data = null;
    // if (StringUtils.isNotBlank(code)) {
    // WithdrawAddress condition = new WithdrawAddress();
    // condition.setCode(code);
    // data = withdrawAddressDAO.select(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "记录不存在");
    // }
    // }
    // return data;
    // }
}
