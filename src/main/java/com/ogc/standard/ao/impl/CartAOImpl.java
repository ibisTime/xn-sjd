/**
 * @Title CartAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:59:58 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICartAO;
import com.ogc.standard.bo.ICartBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.Cart;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.res.XN629712Res;
import com.ogc.standard.enums.ECommodityStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

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
    private ICommoditySpecsBO commoditySpecsBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addToCart(String userId, Long specsId, Long quantity) {
        CommoditySpecs commoditySpecs = commoditySpecsBO
            .getCommoditySpecs(specsId);
        if (commoditySpecs.getInventory() < quantity) {
            throw new BizException("xn0000", "库存不足，无法添加购物车");
        }

        Commodity commodity = commodityBO
            .getCommodity(commoditySpecs.getCommodityCode());
        if (!ECommodityStatus.ON.getCode().equals(commodity.getStatus())) {
            throw new BizException("xn0000", "商品未上架无法添加购物车");
        }

        BigDecimal amount = commoditySpecs.getPrice()
            .multiply(new BigDecimal(quantity));

        String cartCode = null;

        Cart cart = cartBO.getCartByUserSpecs(userId, specsId);
        if (null != cart) {

            cartBO.refreshQuantity(cart.getCode(), cart.getQuantity() + 1);
            cartCode = cart.getCode();

        } else {

            cartCode = cartBO.saveCart(commodity.getShopCode(), userId,
                commodity.getCode(), commodity.getName(), specsId,
                commoditySpecs.getName(), quantity, amount);

        }

        return cartCode;
    }

    @Override
    @Transactional
    public String orderByCart(String applyUser, String applyNote,
            String expressType, String addressCode, List<String> cartCodeList) {

        List<Cart> shopList = cartBO.quertMyShopList(cartCodeList);// 店铺列表
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.CommodityOrder.getCode());// 支付组号

        for (Cart shop : shopList) {

            List<Cart> cartList = cartBO.quertMyShopCartList(shop.getShopCode(),
                cartCodeList);// 店铺下的购物车

            Long quantity = 0l;
            BigDecimal amount = BigDecimal.ZERO;

            // 落地商品订单
            String orderCode = commodityOrderBO.saveOrder(applyUser, applyNote,
                payGroup, expressType, applyUser, applyNote, addressCode);

            // 落地订单明细
            for (Cart cart : cartList) {
                CommoditySpecs specs = commoditySpecsBO
                    .getCommoditySpecs(cart.getSpecsId());
                Commodity commodity = commodityBO
                    .getCommodity(specs.getCommodityCode());

                // 库存检验
                if (cart.getQuantity() > commoditySpecsBO
                    .getInventory(cart.getSpecsId())) {
                    throw new BizException("xn0000", "产品[" + commodity.getName()
                            + "]规格[" + specs.getName() + "]库存不足，不能下单");
                }

                // 落地订单明细
                commodityOrderDetailBO.saveDetail(orderCode,
                    commodity.getShopCode(), commodity.getCode(),
                    commodity.getName(), specs.getId(), specs.getName(),
                    applyUser, cart.getQuantity(), specs.getPrice(),
                    addressCode);

                BigDecimal orderAmount = specs.getPrice()
                    .multiply(BigDecimal.valueOf(cart.getQuantity()));

                // 更新库存
                commoditySpecsBO.refreshInventory(specs.getId(),
                    -cart.getQuantity());

                // 订单商品数量与订单金额累加
                quantity = quantity + cart.getQuantity();
                amount = amount.add(orderAmount);
            }

            // 加上数量与总价
            commodityOrderBO.refreshAmount(quantity, amount, orderCode);

        }

        // 删除购物车
        cartBO.removeCartList(cartCodeList);

        return payGroup;
    }

    @Override
    public void dropCartList(List<String> codeList) {
        cartBO.removeCartList(codeList);
    }

    @Override
    public void dropByShop(String shopCode) {
        cartBO.removeByShop(shopCode);
    }

    @Override
    public List<XN629712Res> queryMyCart(String userId) {
        List<XN629712Res> resList = new ArrayList<XN629712Res>();

        List<Cart> shopCartList = cartBO.quertMyShopList(userId);

        if (CollectionUtils.isNotEmpty(shopCartList)) {
            for (Cart shopCart : shopCartList) {
                Company shop = companyBO.getCompany(shopCart.getShopCode());

                List<Cart> cartList = cartBO
                    .queryCartListByShopUser(shopCart.getShopCode(), userId);

                if (CollectionUtils.isNotEmpty(cartList)) {
                    for (Cart cart : cartList) {
                        Commodity commodity = commodityBO
                            .getCommodity(cart.getCommodityCode());
                        cart.setCommodityPhoto(commodity.getListPic());

                        cart.setLogistics(commodity.getLogistics());
                    }
                }

                XN629712Res res = new XN629712Res(shop.getCode(),
                    shop.getName(), cartList);

                resList.add(res);
            }
        }

        return resList;
    }

    @Override
    public Cart getCart(String code) {
        Cart cart = cartBO.getCart(code);

        init(cart);

        return cart;
    }

    private void init(Cart cart) {
        // 店铺名称
        Company shop = companyBO.getCompany(cart.getShopCode());
        cart.setShopName(shop.getName());
    }

}
