/**
 * @Title IBtcMAddressDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午2:38:36 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BtcMAddress;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午2:38:36 
 * @history:
 */
public interface IBtcMAddressDAO extends IBaseDAO<BtcMAddress> {
    String NAMESPACE = IBtcMAddressDAO.class.getName().concat(".");

    public int updateStatus(BtcMAddress data);
}
