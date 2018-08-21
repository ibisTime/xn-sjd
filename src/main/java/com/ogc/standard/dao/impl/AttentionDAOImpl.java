package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAttentionDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Attention;

@Repository("attentionDAOImpl")
public class AttentionDAOImpl extends AMybatisTemplate
        implements IAttentionDAO {

    @Override
    public int insert(Attention data) {
        return super.insert(NAMESPACE.concat("insert_attention"), data);
    }

    @Override
    public int delete(Attention data) {
        return super.delete(NAMESPACE.concat("delete_attention"), data);
    }

    @Override
    public Attention select(Attention condition) {
        return super.select(NAMESPACE.concat("select_attention"), condition,
            Attention.class);
    }

    @Override
    public long selectTotalCount(Attention condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_attention_count"), condition);
    }

    @Override
    public List<Attention> selectList(Attention condition) {
        return super.selectList(NAMESPACE.concat("select_attention"), condition,
            Attention.class);
    }

    @Override
    public List<Attention> selectList(Attention condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_attention"), start,
            count, condition, Attention.class);
    }

}
