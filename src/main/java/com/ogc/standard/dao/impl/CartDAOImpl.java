/**
 * @Title CartDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:46:24 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICartDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午9:46:24 
 * @history:
 */
@Repository("cartDAOImpl")
public class CartDAOImpl extends AMybatisTemplate implements ICartDAO {

    @Override
    public int insert(Cart data) {
        return super.insert(NAMESPACE.concat("insert_cart"), data);
    }

    @Override
    public int delete(Cart data) {
        return super.delete(NAMESPACE.concat("delete_cart"), data);
    }

    @Override
    public int deleteByShop(Cart cart) {
        return super.delete(NAMESPACE.concat("delete_byShop"), cart);
    }

    @Override
    public Cart select(Cart condition) {
        return super.select(NAMESPACE.concat("select_cart"), condition,
            Cart.class);
    }

    @Override
    public long selectTotalCount(Cart condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cart_count"),
            condition);
    }

    @Override
    public List<Cart> selectList(Cart condition) {
        return super.selectList(NAMESPACE.concat("select_cart"), condition,
            Cart.class);
    }

    @Override
    public List<Cart> selectList(Cart condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cart"), start, count,
            condition, Cart.class);
    }

    @Override
    public List<Cart> selectShopList(Cart cart) {
        return super.selectList(NAMESPACE.concat("select_distinctShop"), cart,
            Cart.class);
    }

}
