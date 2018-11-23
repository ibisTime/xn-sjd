/**
 * @Title CartBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:38:55 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICartBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICartDAO;
import com.ogc.standard.domain.Cart;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午10:38:55 
 * @history:
 */
@Component
public class CartBOImpl extends PaginableBOImpl<Cart> implements ICartBO {

    @Autowired
    private ICartDAO cartDAO;

    @Override
    public String saveCart(String shopCode, String userId, String commodityCode,
            String commodityName, Long specsId, String specsName, Long quantity,
            BigDecimal amount) {
        Cart data = new Cart();
        String code = OrderNoGenerater.generate(EGeneratePrefix.cart.getCode());

        data.setCode(code);
        data.setShopCode(shopCode);
        data.setUserId(userId);
        data.setCommodityCode(commodityCode);
        data.setCommodityName(commodityName);

        data.setSpecsId(specsId);
        data.setSpecsName(specsName);
        data.setQuantity(quantity);
        data.setAmount(amount);
        cartDAO.insert(data);
        return code;
    }

    @Override
    public void removeCartList(List<String> codeList) {
        for (String code : codeList) {
            Cart data = new Cart();
            data.setCode(code);
            cartDAO.delete(data);
        }
    }

    @Override
    public void removeByShop(String shopCode) {
        Cart data = new Cart();
        data.setShopCode(shopCode);
        cartDAO.deleteByShop(data);
    }

    @Override
    public void refreshQuantity(String code, Long quantity, BigDecimal amount) {
        Cart data = new Cart();
        data.setCode(code);
        data.setQuantity(quantity);
        data.setAmount(amount);
        cartDAO.updateQuantity(data);
    }

    @Override
    public List<Cart> queryCartListByUser(String userId) {
        Cart condition = new Cart();
        condition.setUserId(userId);
        return cartDAO.selectList(condition);
    }

    @Override
    public List<Cart> queryCartListByShopUser(String shopCode, String userId) {
        Cart condition = new Cart();
        condition.setShopCode(shopCode);
        condition.setUserId(userId);
        return cartDAO.selectList(condition);
    }

    @Override
    public List<Cart> quertMyShopList(String userId) {
        Cart condition = new Cart();
        condition.setUserId(userId);
        return cartDAO.selectShopList(condition);
    }

    @Override
    public List<Cart> quertMyShopList(List<String> codeList) {
        Cart condition = new Cart();
        condition.setCodeList(codeList);
        return cartDAO.selectShopList(condition);
    }

    @Override
    public List<Cart> quertMyShopCartList(String shopCode,
            List<String> cartList) {
        Cart condition = new Cart();
        condition.setShopCode(shopCode);
        condition.setCodeList(cartList);
        return cartDAO.selectList(condition);
    }

    @Override
    public Cart getCartByUserSpecs(String userId, Long specId) {
        Cart condition = new Cart();
        condition.setUserId(userId);
        condition.setSpecsId(specId);

        Cart cart = cartDAO.select(condition);
        return cart;
    }

    @Override
    public Cart getCart(String code) {
        Cart condition = new Cart();
        condition.setCode(code);
        Cart data = cartDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "不存在该购物车");
        }
        return data;
    }

}
