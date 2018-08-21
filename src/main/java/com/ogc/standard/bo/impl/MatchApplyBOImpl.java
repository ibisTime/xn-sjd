package com.ogc.standard.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IMatchApplyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IMatchApplyDAO;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.enums.EMatchApplyStatus;
import com.ogc.standard.exception.BizException;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:31:28 
 * @history:
 */
@Component
public class MatchApplyBOImpl extends PaginableBOImpl<MatchApply>
        implements IMatchApplyBO {

    @Autowired
    private IMatchApplyDAO matchApplyDAO;

    @Override
    public boolean isMatchApplyExist(String code) {
        MatchApply condition = new MatchApply();
        condition.setCode(code);
        if (matchApplyDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTeamNameExist(String name) {
        MatchApply condition = new MatchApply();
        condition.setTeamName(name);

        List<String> statusList = new ArrayList<String>();
        statusList.add(EMatchApplyStatus.TO_APPROVE.getCode());
        statusList.add(EMatchApplyStatus.APPROVED_YES.getCode());
        condition.setStatusList(statusList);

        if (matchApplyDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveMatchApply(MatchApply data) {
        matchApplyDAO.insert(data);
    }

    @Override
    public void approveMatchApply(String code, String status, String approver,
            String remark) {
        MatchApply data = new MatchApply();
        data.setCode(code);
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        matchApplyDAO.updateApprove(data);
    }

    @Override
    public List<MatchApply> queryMatchApplyList(MatchApply condition) {
        return matchApplyDAO.selectList(condition);
    }

    @Override
    public MatchApply getMatchApply(String code) {
        MatchApply data = null;
        if (StringUtils.isNotBlank(code)) {
            MatchApply condition = new MatchApply();
            condition.setCode(code);
            data = matchApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "参赛申请记录不存在");
            }
        }
        return data;
    }

}
