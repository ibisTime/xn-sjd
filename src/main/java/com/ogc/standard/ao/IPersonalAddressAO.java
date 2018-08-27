package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PersonalAddress;

public interface IPersonalAddressAO {

    static final String DEFAULT_ORDER_COLUMN = "id";

    public String addPersonalAddress(String currency, String address,
            String label, String userId, String isCerti);

    public int dropPersonalAddress(long id);

    public Paginable<PersonalAddress> queryPersonalAddressPage(int start,
            int limit, PersonalAddress condition);

    // public List<PersonalAddress> queryPersonalAddressList(
    // PersonalAddress condition);
    //
    // public PersonalAddress getPersonalAddress(String code);

}
