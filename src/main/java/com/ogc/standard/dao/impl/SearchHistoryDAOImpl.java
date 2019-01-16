package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISearchHistoryDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SearchHistory;

@Repository("searchHistoryDAOImpl")
public class SearchHistoryDAOImpl extends AMybatisTemplate
        implements ISearchHistoryDAO {

    @Override
    public int insert(SearchHistory data) {
        return super.insert(NAMESPACE.concat("insert_searchHistory"), data);
    }

    @Override
    public int delete(SearchHistory data) {
        return super.delete(NAMESPACE.concat("delete_searchHistory"), data);
    }

    @Override
    public SearchHistory select(SearchHistory condition) {
        return super.select(NAMESPACE.concat("select_searchHistory"), condition,
            SearchHistory.class);
    }

    @Override
    public long selectTotalCount(SearchHistory condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_searchHistory_count"), condition);
    }

    @Override
    public List<SearchHistory> selectList(SearchHistory condition) {
        return super.selectList(NAMESPACE.concat("select_searchHistory"),
            condition, SearchHistory.class);
    }

    @Override
    public List<SearchHistory> selectList(SearchHistory condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_searchHistory"), start,
            count, condition, SearchHistory.class);
    }

    @Override
    public void updateSearchDatetime(SearchHistory data) {
        super.update(NAMESPACE.concat("update_searchDatetime"), data);
    }

}
