package com.ogc.standard.bo;

import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SearchHistory;

public interface ISearchHistoryBO extends IPaginableBO<SearchHistory> {

    public String saveSearchHistory(String userId, String type, String content);

    public void refreshSearchDatetime(String code, Date date);

    public List<SearchHistory> querySearchHistoryList(SearchHistory condition);

    public SearchHistory getSearchHistory(String code);

    public SearchHistory getSearchHistory(String userId, String type,
            String content);

}
