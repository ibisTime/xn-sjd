package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISignLogDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SignLog;

@Repository("signLogDAOImpl")
public class SignLogDAOImpl extends AMybatisTemplate implements ISignLogDAO {

    @Override
    public int insert(SignLog data) {
        return super.insert(NAMESPACE.concat("insert_signLog"), data);
    }

    @Override
    public int delete(SignLog data) {
        return super.delete(NAMESPACE.concat("delete_signLog"), data);
    }

    @Override
    public SignLog select(SignLog condition) {
        return super.select(NAMESPACE.concat("select_signLog"), condition,
            SignLog.class);
    }

    @Override
    public long selectTotalCount(SignLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_signLog_count"),
            condition);
    }

    @Override
    public List<SignLog> selectList(SignLog condition) {
        return super.selectList(NAMESPACE.concat("select_signLog"), condition,
            SignLog.class);
    }

    @Override
    public List<SignLog> selectList(SignLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_signLog"), start,
            count, condition, SignLog.class);
    }

}
