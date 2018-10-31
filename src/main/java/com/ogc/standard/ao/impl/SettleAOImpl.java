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
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629902Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EGroupAdoptOrderSettleStatus;
import com.ogc.standard.enums.EJourBizTypeAgent;
import com.ogc.standard.enums.EJourBizTypePlat;
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
        String productCode = null;
        String applyUser = null;

        // 集体订单
        if (ESellType.COLLECTIVE.getCode().equals(settle.getRefType())) {
            GroupAdoptOrder groupAdoptOrder = groupAdoptOrderBO
                .getGroupAdoptOrder(settle.getRefCode());
            settle.setGroupAdoptOrder(groupAdoptOrder);

            productCode = groupAdoptOrder.getProductCode();
            applyUser = groupAdoptOrder.getApplyUser();
        } else {
            // 认养订单
            AdoptOrder adoptOrder = adoptOrderBO
                .getAdoptOrder(settle.getRefCode());
            settle.setAdoptOrder(adoptOrder);

            productCode = adoptOrder.getProductCode();
            applyUser = adoptOrder.getApplyUser();
        }

        // 产品名称
        Product product = productBO.getProduct(productCode);
        settle.setProductName(product.getName());

        // 认养人
        User user = userBO.getUserUnCheck(applyUser);
        if (null != user) {
            settle.setApplyUserName(user.getMobile());
        }
    }

    @Override
    public XN629902Res getSettleTotalAmount(String userId, String status,
            Date createStartDatetime, Date createEndDatetime) {
        return new XN629902Res(settleBO.getTotalAmount(userId, status,
            createStartDatetime, createEndDatetime));
    }

}
