package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EToolOrderStatus;
import com.ogc.standard.enums.EToolStatus;
import com.ogc.standard.enums.EToolType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ToolOrderAOImpl implements IToolOrderAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IToolBO toolBO;

    @Autowired
    private IToolOrderBO toolOrderBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IToolUseRecordBO toolUseRecordBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private ICarbonBubbleOrderAO carbonBubbleOrderAO;

    @Override
    @Transactional
    public String buyTool(String toolCode, String userId) {

        // 查询道具
        Tool tool = toolBO.getTool(toolCode);
        if (EToolStatus.DOWN.getCode().equals(tool.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "当前道具已下架！");
        }

        // 验证用户
        User user = userBO.getUser(userId);

        // 积分验证
        BigDecimal quantity = tool.getPrice();
        Account userJfAccount = accountBO.getAccountByUser(userId,
            ECurrency.JF.getCode());
        if (quantity.compareTo(userJfAccount.getAmount()) == 1) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "积分余额不足，无法购买！");
        }

        // 落地订单
        String toolOrderCode = toolOrderBO.saveToolOrder(tool, user);

        // 积分划转
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        if (quantity.compareTo(sysJfAccount.getAmount()) != 1) {
            accountBO.transAmount(userJfAccount, sysJfAccount, quantity,
                EJourBizTypeUser.TOOL_BUY.getCode(),
                EJourBizTypePlat.TOOL_BUY.getCode(),
                EJourBizTypeUser.TOOL_BUY.getValue(),
                EJourBizTypePlat.TOOL_BUY.getValue(), userId);
        }

        return toolOrderCode;
    }

    @Override
    @Transactional
    public void useTool(String toolOrderCode, String adoptTreeCode,
            String userId) {

        // 验证道具
        ToolOrder toolOrder = toolOrderBO.getToolOrder(toolOrderCode);
        if (!toolOrder.getUserId().equals(userId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "非本人操作");
        }
        if (toolOrder.getStatus().equals(EToolOrderStatus.USED.getCode())) { // （0未使用/1已使用）
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具已被使用！");
        }
        if (CollectionUtils.isNotEmpty(
            toolUseRecordBO.queryTreeToolRecordList(toolOrder.getCode()))) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "已有相同道具正在生效中！");
        }

        // 验证认养权
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(adoptTreeCode);
        if (!EAdoptOrderTreeStatus.ADOPT.getCode()
            .equals(adoptOrderTree.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前认养权不在认养中！");
        }

        Tool tool = toolBO.getTool(toolOrder.getToolCode());

        // 一键收取
        if (EToolType.GET_ALL.getCode().equals(tool.getType())) {
            BigDecimal quantity = carbonBubbleOrderAO
                .takeCarbonBubbleByAdopt(adoptTreeCode, userId);

            bizLogBO.useGetall(adoptTreeCode, adoptOrderTree.getCurrentHolder(),
                userId, quantity);
        }

        // 添加使用保护罩日志
        if (EToolType.SHIELD.getCode().equals(tool.getType())) {
            bizLogBO.useShelter(adoptTreeCode,
                adoptOrderTree.getCurrentHolder(), userId);
        }

        // 落地使用记录
        toolUseRecordBO.saveToolUseRecord(toolOrder, adoptOrderTree, userId);

        // 刷新订单状态
        toolOrderBO.refreshStatus(toolOrder);
    }

    @Override
    public Paginable<ToolOrder> queryToolOrderPage(int start, int limit,
            ToolOrder condition) {

        Paginable<ToolOrder> page = toolOrderBO.getPaginable(start, limit,
            condition);

        if (null != page) {
            for (ToolOrder toolOrder : page.getList()) {
                init(toolOrder);
            }
        }

        return page;
    }

    @Override
    public List<ToolOrder> queryToolOrderList(ToolOrder condition) {

        List<ToolOrder> toolOrderList = toolOrderBO
            .queryToolOrderList(condition);

        for (ToolOrder toolOrder : toolOrderList) {
            init(toolOrder);
        }

        return toolOrderList;
    }

    @Override
    public ToolOrder getToolOrder(String code) {

        ToolOrder toolOrder = toolOrderBO.getToolOrder(code);
        init(toolOrder);

        return toolOrder;
    }

    private void init(ToolOrder toolOrder) {
        User userInfo = userBO.getUser(toolOrder.getUserId());
        toolOrder.setUserInfo(userInfo);
    }

}
