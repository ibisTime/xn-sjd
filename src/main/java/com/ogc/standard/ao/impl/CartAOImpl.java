/**
 * @Title CartAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:59:58 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICartAO;
import com.ogc.standard.bo.ICartBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午10:59:58 
 * @history:
 */
@Service
public class CartAOImpl implements ICartAO {

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Override
    public String addToCart(String userId, String commodityCode,
            String commodityName, Long specsId, String specsName, Long quantity) {
        userBO.getUser(userId);
        commoditySpecsBO.getInventory(specsId);
        return cartBO.saveCart(userId, commodityCode, commodityName, specsId,
            specsName, quantity);
    }

    @Override
    public void dropCartList(List<String> codeList) {
        cartBO.removeCartList(codeList);
    }

    @Override
    public List<Cart> queryMyCart(String userId) {
        userBO.getUser(userId);
        return cartBO.queryCartList(userId);
    }

    @Override
    public Cart getCart(String code) {
        return cartBO.getCart(code);
    }

}
