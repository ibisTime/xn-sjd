package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SearchHistory;

@Component
public interface ISearchHistoryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSearchHistory(String userId, String type, String content);

    public Paginable<SearchHistory> querySearchHistoryPage(int start, int limit,
            SearchHistory condition);

    public List<SearchHistory> querySearchHistoryList(SearchHistory condition);

    public SearchHistory getSearchHistory(String code);

}
