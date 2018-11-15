/**
 * @Title ICartBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:50:57 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Cart;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午9:50:57 
 * @history:
 */
public interface ICartBO extends IPaginableBO<Cart> {

    public String saveCart(String shopCode, String userId, String commodityCode,
            String commodityName, Long specsId, String specsName, Long quantity,
            BigDecimal amount);

    public void removeCartList(List<String> codeList);

    // 根据店铺删购物车
    public void removeByShop(String shopCode);

    public List<Cart> queryCartListByUser(String userId);

    public List<Cart> queryCartListByShop(String shopCode);

    // 查询我的店铺
    public List<Cart> quertMyShopList(String userId);

    public Cart getCart(String code);
}
