package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESettleStatus;

@Service
public class SettleAOImpl implements ISettleAO {

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    @Transactional
    public void approveSettleByRef(String refCode, String approveResult,
            String handleNote) {
        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ESettleStatus.SETTLE_YES.getCode();

            // 账户加钱

        } else {
            status = ESettleStatus.SETTLE_NO.getCode();
        }

        settleBO.refreshStatusByRef(refCode, status, handleNote);
    }

    @Override
    public Paginable<Settle> querySettlePage(int start, int limit,
            Settle condition) {
        return settleBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Settle> querySettleList(Settle condition) {
        return settleBO.querySettleList(condition);
    }

    @Override
    public Settle getSettle(String code) {
        return settleBO.getSettle(code);
    }

}
