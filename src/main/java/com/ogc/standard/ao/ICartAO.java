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
import com.ogc.standard.dto.res.XN629712Res;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午10:50:55 
 * @history:
 */
public interface ICartAO {

    public String addToCart(String userId, Long specsId, Long quantity);

    public void dropCartList(List<String> codeList);

    public void dropByShop(String shopCode);

    public List<XN629712Res> queryMyCart(String userId);

    public String orderByCart(String applyUser, String applyNote,
            String expressType, String addressCode, List<String> cartList);

    public Cart getCart(String code);
}
