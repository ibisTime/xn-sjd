package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IOfficialSealDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.OfficialSeal;

@Repository("officialSealDAOImpl")
public class OfficialSealDAOImpl extends AMybatisTemplate
        implements IOfficialSealDAO {

    @Override
    public int insert(OfficialSeal data) {
        return super.insert(NAMESPACE.concat("insert_officialSeal"), data);
    }

    @Override
    public int delete(OfficialSeal data) {
        return super.delete(NAMESPACE.concat("delete_officialSeal"), data);
    }

    @Override
    public int updateOfficialSeal(OfficialSeal officialSeal) {
        return super.update(NAMESPACE.concat("update_officialSeal"),
            officialSeal);
    }

    @Override
    public OfficialSeal select(OfficialSeal condition) {
        return super.select(NAMESPACE.concat("select_officialSeal"), condition,
            OfficialSeal.class);
    }

    @Override
    public long selectTotalCount(OfficialSeal condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_officialSeal_count"), condition);
    }

    @Override
    public List<OfficialSeal> selectList(OfficialSeal condition) {
        return super.selectList(NAMESPACE.concat("select_officialSeal"),
            condition, OfficialSeal.class);
    }

    @Override
    public List<OfficialSeal> selectList(OfficialSeal condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_officialSeal"), start,
            count, condition, OfficialSeal.class);
    }

}
