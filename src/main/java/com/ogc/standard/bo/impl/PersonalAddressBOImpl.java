package com.ogc.standard.bo.impl;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPersonalAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.domain.PersonalAddress;

@Component
public class PersonalAddressBOImpl extends PaginableBOImpl<PersonalAddress>
        implements IPersonalAddressBO {

    // @Autowired
    // private IWithdrawAddressDAO withdrawAddressDAO;
    //
    // @Override
    // public String saveWithdrawAddress(String currency, String address,
    // String label, String userId, String status) {
    // String code = null;
    // Date now = new Date();
    // WithdrawAddress data = new WithdrawAddress();
    // code = OrderNoGenerater.generate("AD");
    // data.setCode(code);
    // data.setCurrency(currency);
    // data.setAddress(address);
    // data.setUserId(userId);
    // data.setLabel(label);
    // data.setStatus(status);
    // data.setCreateDatetime(now);
    // data.setUpdater(userId);
    // data.setUpdateDatetime(now);
    // withdrawAddressDAO.insert(data);
    // return code;
    // }
    //
    // @Override
    // public int removeWithdrawAddress(String code) {
    // int count = 0;
    // if (StringUtils.isNotBlank(code)) {
    // WithdrawAddress data = new WithdrawAddress();
    // data.setCode(code);
    // count = withdrawAddressDAO.delete(data);
    // }
    // return count;
    // }
    //
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
