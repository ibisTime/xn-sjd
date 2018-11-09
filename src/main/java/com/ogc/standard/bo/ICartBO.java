/**
 * @Title ICartBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:50:57 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午9:50:57 
 * @history:
 */
public interface ICartBO extends IPaginableBO<Cart> {

    public String saveCart(String userId, String commodityCode,
            String commodityName, Long specsId, String specsName, Long quantity);

    public void removeCartList(List<String> codeList);

    public List<Cart> queryCartList(String userId);

    public Cart getCart(String code);
}
