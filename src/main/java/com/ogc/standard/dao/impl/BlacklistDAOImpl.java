package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IBlacklistDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Blacklist;

@Repository("blacklistDAOImpl")
public class BlacklistDAOImpl extends AMybatisTemplate
        implements IBlacklistDAO {

    @Override
    public int insert(Blacklist data) {
        return super.insert(NAMESPACE.concat("insert_blacklist"), data);
    }

    @Override
    public int delete(Blacklist data) {
        return 0;
    }

    @Override
    public Blacklist select(Blacklist condition) {
        return super.select(NAMESPACE.concat("select_blacklist"), condition,
            Blacklist.class);
    }

    @Override
    public long selectTotalCount(Blacklist condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_blacklist_count"), condition);
    }

    @Override
    public List<Blacklist> selectList(Blacklist condition) {
        return super.selectList(NAMESPACE.concat("select_blacklist"), condition,
            Blacklist.class);
    }

    @Override
    public List<Blacklist> selectList(Blacklist condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_blacklist"), start,
            count, condition, Blacklist.class);
    }

    @Override
    public int updateStatus(Blacklist data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
