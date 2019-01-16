package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISearchHistoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ISearchHistoryDAO;
import com.ogc.standard.domain.SearchHistory;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class SearchHistoryBOImpl extends PaginableBOImpl<SearchHistory>
        implements ISearchHistoryBO {

    @Autowired
    private ISearchHistoryDAO searchHistoryDAO;

    @Override
    public String saveSearchHistory(String userId, String type,
            String content) {
        SearchHistory data = new SearchHistory();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SEARCH_HISTORY.getCode());
        data.setCode(code);
        data.setUserId(userId);
        data.setType(type);
        data.setContent(content);
        data.setSearchDatetime(new Date());

        searchHistoryDAO.insert(data);

        return code;
    }

    @Override
    public void refreshSearchDatetime(String code, Date date) {
        SearchHistory searchHistory = new SearchHistory();

        searchHistory.setCode(code);
        searchHistory.setSearchDatetime(date);

        searchHistoryDAO.updateSearchDatetime(searchHistory);
    }

    @Override
    public List<SearchHistory> querySearchHistoryList(SearchHistory condition) {
        return searchHistoryDAO.selectList(condition);
    }

    @Override
    public SearchHistory getSearchHistory(String code) {
        SearchHistory data = null;
        if (StringUtils.isNotBlank(code)) {
            SearchHistory condition = new SearchHistory();
            condition.setCode(code);
            data = searchHistoryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "搜索历史不存在");
            }
        }
        return data;
    }

    @Override
    public SearchHistory getSearchHistory(String userId, String type,
            String content) {
        SearchHistory data = new SearchHistory();

        SearchHistory condition = new SearchHistory();
        condition.setUserId(userId);
        condition.setType(type);
        condition.setContent(content);

        data = searchHistoryDAO.select(condition);

        return data;
    }

}
