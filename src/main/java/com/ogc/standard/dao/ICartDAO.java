/**
 * @Title ICartDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:45:07 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午9:45:07 
 * @history:
 */
public interface ICartDAO extends IBaseDAO<Cart> {
    String NAMESPACE = ICartDAO.class.getName().concat(".");

}
