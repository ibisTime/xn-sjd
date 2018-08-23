package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IKeywordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Keyword;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:35:28 
 * @history:
 */
@Repository("keywordDAOImpl")
public class KeywordDAOImpl extends AMybatisTemplate implements IKeywordDAO {

    @Override
    public int insert(Keyword data) {
        return super.insert(NAMESPACE.concat("insert_keyword"), data);
    }

    @Override
    public int delete(Keyword data) {
        return super.delete(NAMESPACE.concat("delete_keyword"), data);
    }

    @Override
    public int updateKeyword(Keyword data) {
        return super.update(NAMESPACE.concat("update_keyword"), data);
    }

    @Override
    public Keyword select(Keyword condition) {
        return super.select(NAMESPACE.concat("select_keyword"), condition,
            Keyword.class);
    }

    @Override
    public long selectTotalCount(Keyword condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_keyword_count"),
            condition);
    }

    @Override
    public List<Keyword> selectList(Keyword condition) {
        return super.selectList(NAMESPACE.concat("select_keyword"), condition,
            Keyword.class);
    }

    @Override
    public List<Keyword> selectList(Keyword condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_keyword"), start,
            count, condition, Keyword.class);
    }

}
