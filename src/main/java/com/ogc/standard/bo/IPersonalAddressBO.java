package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PersonalAddress;

public interface IPersonalAddressBO extends IPaginableBO<PersonalAddress> {

    public String savePersonalAddress(String symbol, String address,
            String label, String userId, String status);

    public int removePersonalAddress(long id);

    // public int refreshPersonalAddress(PersonalAddress data);
    //
    // public List<PersonalAddress> queryPersonalAddressList(
    // PersonalAddress condition);
    //
    // public PersonalAddress getPersonalAddress(String code);

}
