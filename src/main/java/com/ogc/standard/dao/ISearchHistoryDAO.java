package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SearchHistory;

/**
 * 搜索历史
 * @author: silver 
 * @since: Jan 15, 2019 3:54:28 PM 
 * @history:
 */
public interface ISearchHistoryDAO extends IBaseDAO<SearchHistory> {
    String NAMESPACE = ISearchHistoryDAO.class.getName().concat(".");

    public void updateSearchDatetime(SearchHistory data);

}
