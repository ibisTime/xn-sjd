package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ShareRecord;

/**
 * 分享记录
 * @author: silver 
 * @since: Sep 29, 2018 9:50:38 PM 
 * @history:
 */
public interface IShareRecordDAO extends IBaseDAO<ShareRecord> {
    String NAMESPACE = IShareRecordDAO.class.getName().concat(".");
}
