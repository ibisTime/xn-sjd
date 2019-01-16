package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISearchHistoryAO;
import com.ogc.standard.bo.ISearchHistoryBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SearchHistory;

@Service
public class SearchHistoryAOImpl implements ISearchHistoryAO {

    @Autowired
    private ISearchHistoryBO searchHistoryBO;

    @Override
    public String addSearchHistory(String userId, String type, String content) {
        SearchHistory oldSearchHistory = searchHistoryBO
            .getSearchHistory(userId, type, content);
        String code = null;

        if (null == oldSearchHistory) {
            code = searchHistoryBO.saveSearchHistory(userId, type, content);
        } else {
            code = oldSearchHistory.getCode();
            searchHistoryBO.refreshSearchDatetime(code, new Date());
        }

        // TODO

        return code;
    }

    @Override
    public Paginable<SearchHistory> querySearchHistoryPage(int start, int limit,
            SearchHistory condition) {
        return searchHistoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SearchHistory> querySearchHistoryList(SearchHistory condition) {
        return searchHistoryBO.querySearchHistoryList(condition);
    }

    @Override
    public SearchHistory getSearchHistory(String code) {
        return searchHistoryBO.getSearchHistory(code);
    }
}
