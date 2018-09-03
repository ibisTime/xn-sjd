package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPersonalAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PersonalAddress;

@Repository("personalAddressDAOImpl")
public class PersonalAddressDAOImpl extends AMybatisTemplate
        implements IPersonalAddressDAO {

    @Override
    public int insert(PersonalAddress data) {
        return super.insert(NAMESPACE.concat("insert_personalAddress"), data);
    }

    @Override
    public int delete(PersonalAddress data) {
        return super.delete(NAMESPACE.concat("delete_personalAddress"), data);
    }

    @Override
    public PersonalAddress select(PersonalAddress condition) {
        return super.select(NAMESPACE.concat("select_personalAddress"),
            condition, PersonalAddress.class);
    }

    @Override
    public long selectTotalCount(PersonalAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_personalAddress_count"), condition);
    }

    @Override
    public List<PersonalAddress> selectList(PersonalAddress condition) {
        return super.selectList(NAMESPACE.concat("select_personalAddress"),
            condition, PersonalAddress.class);
    }

    @Override
    public List<PersonalAddress> selectList(PersonalAddress condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_personalAddress"),
            start, count, condition, PersonalAddress.class);
    }

}
