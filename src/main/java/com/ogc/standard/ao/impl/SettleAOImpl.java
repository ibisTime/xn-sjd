package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IPresellOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629902Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommodityOrderSettleStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EGroupAdoptOrderSettleStatus;
import com.ogc.standard.enums.EJourBizTypeAgent;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EPresellOrderSettleStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class SettleAOImpl implements ISettleAO {

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private IGroupAdoptOrderBO groupAdoptOrderBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IPresellOrderBO presellOrderBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Override
    @Transactional
    public void approveSettleByRefCode(String refCode, String refType,
            String approveResult, String handler, String handleNote) {
        if (ESellType.COLLECTIVE.getCode().equals(refType)) {
            GroupAdoptOrder data = groupAdoptOrderBO
                .getGroupAdoptOrder(refCode);
            if (!EGroupAdoptOrderSettleStatus.TO_SETTLE.getCode()
                .equals(data.getSettleStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "订单不是待结算状态");
            }

            groupAdoptOrderBO.refreshSettle(data, approveResult, handler,
                handleNote);
        } else if (ESellType.PRESELL.getCode().equals(refType)) {
            PresellOrder data = presellOrderBO.getPresellOrder(refCode);
            if (!EPresellOrderSettleStatus.TO_SETTLE.getCode()
                .equals(data.getSettleStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "订单不是待结算状态");
            }

            presellOrderBO.refreshSettleStatus(data, approveResult, handler,
                handleNote);
        } else if (ESellType.COMMODITY.getCode().equals(refType)) {
            CommodityOrder data = commodityOrderBO.getCommodityOrder(refCode);
            if (!ECommodityOrderSettleStatus.TO_SETTLE.getCode()
                .equals(data.getSettleStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "订单不是待结算状态");
            }

            commodityOrderBO.refreshSettleStatus(data, approveResult, handler,
                handleNote);
        } else {
            AdoptOrder data = adoptOrderBO.getAdoptOrder(refCode);
            if (!EAdoptOrderSettleStatus.TO_SETTLE.getCode()
                .equals(data.getSettleStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "订单不是待结算状态");
            }

            adoptOrderBO.refreshSettleStatus(data, approveResult, handler,
                handleNote);
        }

        if (EBoolean.YES.getCode().equals(approveResult)) {
            List<Settle> settleList = settleBO.querySettleList(refCode);
            for (Settle settle : settleList) {
                // 余额划转
                accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                    settle.getUserId(), ECurrency.CNY.getCode(),
                    settle.getAmount(), EJourBizTypePlat.ADOPT_DIST.getCode(),
                    EJourBizTypeAgent.AGENT_DEDUCT.getCode(),
                    settle.getRefNote(), settle.getRefNote(), settle.getCode());
            }
        }

        settleBO.refreshStatusByRefCode(refCode, approveResult, handler,
            handleNote);
    }

    @Override
    public Paginable<Settle> querySettlePage(int start, int limit,
            Settle condition) {
        Paginable<Settle> page = settleBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Settle settle : page.getList()) {
                initSettle(settle);
            }
        }

        return page;
    }

    @Override
    public List<Settle> querySettleList(Settle condition) {
        return settleBO.querySettleList(condition);
    }

    @Override
    public Settle getSettle(String code) {
        Settle settle = settleBO.getSettle(code);

        initSettle(settle);

        return settle;
    }

    private void initSettle(Settle settle) {
        String productName = null;
        String applyUser = null;

        if (ESellType.COLLECTIVE.getCode().equals(settle.getRefType())) {

            // 集体订单
            GroupAdoptOrder groupAdoptOrder = groupAdoptOrderBO
                .getGroupAdoptOrder(settle.getRefCode());
            settle.setGroupAdoptOrder(groupAdoptOrder);

            Product product = productBO
                .getProduct(groupAdoptOrder.getProductCode());
            productName = product.getName();
            applyUser = groupAdoptOrder.getApplyUser();

        } else if (ESellType.PRESELL.getCode().equals(settle.getRefType())) {

            // 预售订单
            PresellOrder presellOrder = presellOrderBO
                .getPresellOrder(settle.getRefCode());
            settle.setPresellOrder(presellOrder);

            productName = presellOrder.getProductName();
            applyUser = presellOrder.getApplyUser();

        } else if (ESellType.COMMODITY.getCode().equals(settle.getRefType())) {

            // 商城订单
            CommodityOrder commodityOrder = commodityOrderBO
                .getCommodityOrder(settle.getRefCode());
            settle.setCommodityOrder(commodityOrder);

            List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                .queryOrderDetail(commodityOrder.getCode());
            StringBuffer productNames = new StringBuffer();

            if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                int detailOrderCount = 1;
                for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetailList) {
                    productNames
                        .append(commodityOrderDetail.getCommodityName());

                    if (detailOrderCount++ < commodityOrderDetailList.size()) {
                        productNames.append(". ");
                    }
                }
            }
            productName = productNames.toString();
            applyUser = commodityOrder.getApplyUser();

        } else {

            // 认养订单
            AdoptOrder adoptOrder = adoptOrderBO
                .getAdoptOrder(settle.getRefCode());
            settle.setAdoptOrder(adoptOrder);

            Product product = productBO.getProduct(adoptOrder.getProductCode());
            productName = product.getName();
            applyUser = adoptOrder.getApplyUser();

        }

        // 产品名称
        settle.setProductName(productName);

        // 认养人
        User user = userBO.getUserUnCheck(applyUser);
        if (null != user) {
            settle.setApplyUserName(user.getMobile());
        }

        // 代理用户
        AgentUser agentUser = agentUserBO
            .getAgentUserUnCheck(settle.getUserId());
        settle.setUserName(agentUser.getMobile());
    }

    @Override
    public XN629902Res getSettleTotalAmount(String userId, String status,
            Date createStartDatetime, Date createEndDatetime) {
        return new XN629902Res(settleBO.getTotalAmount(userId, status,
            createStartDatetime, createEndDatetime));
    }

}
