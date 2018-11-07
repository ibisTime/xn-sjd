/**
 * @Title ICartAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:50:55 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午10:50:55 
 * @history:
 */
public interface ICartAO {

    public String addToCart(String userId, String commodityCode,
            String commodityName, String specsCode, String specsName,
            Long quantity);

    public void dropCartList(List<String> codeList);

    public List<Cart> queryMyCart(String userId);

    public Cart getCart(String code);
}
