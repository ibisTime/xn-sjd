/**
 * @Title IBtcWAddressDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午7:49:40 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BtcWAddress;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午7:49:40 
 * @history:
 */
public interface IBtcWAddressDAO extends IBaseDAO<BtcWAddress> {
    String NAMESPACE = IBtcWAddressDAO.class.getName().concat(".");

    public int updateStatus(BtcWAddress data);
}
