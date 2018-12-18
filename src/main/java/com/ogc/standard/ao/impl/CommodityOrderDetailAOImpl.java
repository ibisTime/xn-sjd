/**
 * @Title CommodityOrderDetailAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午8:00:23 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.dto.res.XN629730Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EFilterFlag;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午8:00:23 
 * @history:
 */
@Service
public class CommodityOrderDetailAOImpl implements ICommodityOrderDetailAO {

    static final Logger logger = LoggerFactory
        .getLogger(CommodityOrderDetailAOImpl.class);

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public XN629730Res comment(String code, String userId, String content) {
        CommodityOrderDetail commodityOrderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(code);

        if (!ECommodityOrderDetailStatus.TO_COMMENT.getCode()
            .equals(commodityOrderDetail.getStatus())) {
            throw new BizException("xn0000", "订单未处于可评价状态");
        }

        // 关键字过滤
        List<Keyword> keywordList = keywordBO.checkContent(content);
        String status = ECommentStatus.RELEASED.getCode();
        String filterFlag = null;

        if (CollectionUtils.isNotEmpty(keywordList)) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                throw new BizException("xn0000", "你的评论含有拦截词:"
                        + keywordList.get(0).getWord() + " 无法评论，请重新编辑再评论");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                for (Keyword keyword : keywordList) {
                    content = keywordBO.replaceKeyword(content,
                        keyword.getWord());
                }

                filterFlag = EFilterFlag.REPLACED.getCode();
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                status = ECommentStatus.TO_APPROVE.getCode();
            }
        }

        if (ECommentStatus.RELEASED.getCode().equals(status)
                && null == filterFlag) {
            filterFlag = EFilterFlag.NORMAN.getCode();
        } else if (ECommentStatus.TO_APPROVE.getCode().equals(status)) {
            filterFlag = EFilterFlag.TO_APPROVE.getCode();
        }

        String commentCode = commentBO.saveComment(userId, content,
            commodityOrderDetail.getCommodityCode(), null, null, status);

        // 更新明细状态
        commodityOrderDetailBO.comment(code);

        // 更新订单状态
        commodityOrderBO.refreshFinish(commodityOrderDetail.getOrderCode());

        return new XN629730Res(commentCode, filterFlag);
    }

    @Override
    public void refreshDkAmount(String payGroup) {

        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        Double adoptDkRate = Double
            .valueOf(configMap.get(SysConstants.COMMODITY_DK_RATE));// 1人民币兑换多少积分

        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryCommodityOrderByPayGroup(payGroup);

        if (CollectionUtils.isNotEmpty(commodityOrderList)) {
            for (CommodityOrder commodityOrder : commodityOrderList) {
                List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                    .queryOrderDetail(commodityOrder.getCode());

                if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                    BigDecimal detailCnyAmount = BigDecimal.ZERO;
                    BigDecimal detailJfAmount = BigDecimal.ZERO;

                    for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetailList) {
                        detailCnyAmount = AmountUtil.mul(
                            commodityOrderDetail.getAmount(),
                            commodityOrderDetail.getMaxJfdkRate() * 0.01);
                        detailJfAmount = AmountUtil.mul(detailCnyAmount,
                            adoptDkRate);

                        commodityOrderDetailBO.refreshDkAmount(
                            commodityOrderDetail.getCode(), detailCnyAmount,
                            detailJfAmount, null, commodityOrderDetail
                                .getAmount().subtract(detailCnyAmount),
                            commodityOrder.getPayType());

                    }
                }

            }
        }
    }

    @Override
    public Paginable<CommodityOrderDetail> queryDetailPage(int start, int limit,
            CommodityOrderDetail condition) {
        Paginable<CommodityOrderDetail> page = commodityOrderDetailBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (CommodityOrderDetail commodityOrderDetail : page.getList()) {
                init(commodityOrderDetail);
            }
        }

        return page;
    }

    @Override
    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition) {
        List<CommodityOrderDetail> list = commodityOrderDetailBO
            .queryDetailList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (CommodityOrderDetail commodityOrderDetail : list) {
                init(commodityOrderDetail);
            }
        }

        return list;
    }

    @Override
    public CommodityOrderDetail getCommodityOrderDetail(String code) {
        CommodityOrderDetail commodityOrderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(code);

        init(commodityOrderDetail);

        return commodityOrderDetail;
    }

    private void init(CommodityOrderDetail commodityOrderDetail) {
        // 收货地址
        Address address = addressBO
            .getAddress(commodityOrderDetail.getAddressCode());
        commodityOrderDetail.setAddress(address);

        // 店铺名称
        Company shop = companyBO.getCompany(commodityOrderDetail.getShopCode());
        commodityOrderDetail.setShopName(shop.getName());

        // 卖家
        commodityOrderDetail.setSellerName(shop.getName());

        // 支付流水编号
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(commodityOrderDetail.getOrderCode());
        Account userCnyAccount = accountBO.getAccountByUser(
            commodityOrder.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(commodityOrder.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            commodityOrderDetail.setJourCode(jourList.get(0).getCode());
        }

        // 物流方式
        Commodity commodity = commodityBO
            .getCommodity(commodityOrderDetail.getCommodityCode());
        commodityOrderDetail.setLogistics(commodity.getLogistics());

    }

}
