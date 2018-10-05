package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.EToolOrderStatus;
import com.ogc.standard.enums.EToolStatus;
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

        // 落地订单
        String toolOrderCode = toolOrderBO.saveToolOrder(tool, user);

        // 划转积分
        accountBO.transAmount(userId, ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), tool.getPrice(),
            EJourBizTypeUser.TOOL_BUY.getCode(),
            EJourBizTypePlat.TOOL_BUY.getCode(),
            EJourBizTypeUser.TOOL_BUY.getValue(),
            EJourBizTypePlat.TOOL_BUY.getValue(), toolOrderCode);

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
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "当前道具已被使用！");
        }

        // 验证用户
        User user = userBO.getUser(toolOrder.getUserId());

        // 验证认养权
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(adoptTreeCode);
        if (!EAdoptOrderTreeStatus.ADOPT.getCode().equals(
            adoptOrderTree.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前认养权不在认养中！");
        }

        // 落地使用记录
        toolUseRecordBO.saveToolUseRecord(toolOrder, adoptOrderTree, user);

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