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
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.ICartBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Cart;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.res.XN629712Res;
import com.ogc.standard.enums.ECommodityStatus;
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
    private IUserBO userBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addToCart(String userId, String commodityCode,
            String commodityName, Long specsId, String specsName,
            Long quantity) {
        CommoditySpecs commoditySpecs = commoditySpecsBO
            .getCommoditySpecs(specsId);
        if (commoditySpecs.getInventory() < quantity) {
            throw new BizException("xn0000", "库存不足，无法添加购物车");
        }
        Commodity commodity = commodityBO.getCommodity(commodityCode);
        if (!ECommodityStatus.ON.getCode().equals(commodity.getStatus())) {
            throw new BizException("xn0000", "商品未上架无法添加购物车");
        }

        BigDecimal amount = commoditySpecs.getPrice()
            .multiply(new BigDecimal(quantity));

        return cartBO.saveCart(commodity.getShopCode(), userId, commodityCode,
            commodityName, specsId, specsName, quantity, amount);
    }

    @Override
    @Transactional
    public String orderByCart(String applyUser, String applyNote,
            String expressType, String addressCode, List<String> cartList) {
        // 用户检验
        userBO.getUser(applyUser);

        // 地址检验
        Address address = addressBO.getAddress(addressCode);
        if (null == address) {
            throw new BizException("xn0000", "该地址不存在");
        }
        if (!address.getUserId().equals(applyUser)) {
            throw new BizException("xn0000", "该地址不属于你");
        }

        // 落地订单
        String orderCode = commodityOrderBO.saveOrder(applyUser, applyNote,
            expressType, applyUser, applyNote, addressCode);

        Long quantity = Long.valueOf(0);
        BigDecimal amount = BigDecimal.ZERO;
        for (String code : cartList) {
            // 购物车检验
            Cart cart = cartBO.getCart(code);

            CommoditySpecs specs = commoditySpecsBO
                .getCommoditySpecs(cart.getSpecsId());

            // 商品检验
            Commodity commodity = commodityBO
                .getCommodity(specs.getCommodityCode());
            // 库存检验
            if (cart.getQuantity() > commoditySpecsBO
                .getInventory(cart.getSpecsId())) {
                throw new BizException("xn0000", "产品[" + commodity.getName()
                        + "]规格[" + specs.getName() + "]库存不足，不能下单");
            }
            if (!commodityBO.isOnShelf(commodity.getCode())) {
                throw new BizException("xn0000",
                    "订单中有未上架产品[" + commodity.getName() + "]无法下单");
            }
            // 落地单店铺订单
            commodityOrderDetailBO.saveDetail(orderCode,
                commodity.getShopCode(), commodity.getCode(),
                commodity.getName(), specs.getId(), specs.getName(), applyUser,
                cart.getQuantity(), specs.getPrice(), addressCode);
            BigDecimal orderAmount = specs.getPrice()
                .multiply(BigDecimal.valueOf(cart.getQuantity()));
            // 库存减少
            commoditySpecsBO.inventoryDecrease(specs.getId(),
                -cart.getQuantity());
            // 订单商品数量与订单金额累加
            quantity = quantity + cart.getQuantity();
            amount = amount.add(orderAmount);
        }
        // 加上数量与总价
        commodityOrderBO.refreshAmount(quantity, amount, orderCode);
        // 删除购物车
        cartBO.removeCartList(cartList);
        return orderCode;
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
                    .queryCartListByShop(shopCart.getShopCode());

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
