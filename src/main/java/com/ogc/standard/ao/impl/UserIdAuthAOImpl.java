/**
 * @Title UserIdAuthAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午5:49:41 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IUserIdAuthAO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserIdAuthBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.dao.IUserIdAuthDAO;
import com.ogc.standard.domain.UserIdAuth;
import com.ogc.standard.dto.req.XN805160Req;
import com.ogc.standard.enums.EApproveStatus;
import com.ogc.standard.enums.EResultType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午5:49:41 
 * @history:
 */
@Service
public class UserIdAuthAOImpl implements IUserIdAuthAO {

    @Autowired
    private IUserIdAuthBO userIdAuthBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IUserIdAuthDAO userIdAuthDAO;

    @Override
    public void apply(XN805160Req req) {
        UserIdAuth condition = new UserIdAuth();
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(EApproveStatus.PASS.getCode());
        if (userIdAuthDAO.select(condition) != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该用户已认证成功");
        }
        condition.setStatus(EApproveStatus.TOAPPROVE.getCode());
        if (userIdAuthDAO.select(condition) != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该用户已有认证审批中");
        }
        UserIdAuth data = new UserIdAuth();
        data.setApplyUser(req.getApplyUser());
        data.setRealName(req.getRealName());
        data.setCountry(req.getCountry());
        data.setIdFace(req.getIdFace());
        data.setIdKind(req.getIdKind());
        data.setIdHold(req.getIdHold());
        data.setIdNo(req.getIdNo());
        data.setIdOppo(req.getIdOppo());
        userIdAuthBO.saveApply(data);
    }

    @Override
    public void approve(Long id, String approveResult, String approveUser,
            String remark) {
        UserIdAuth data = userIdAuthBO.getUserIdAuth(id);
        if (EResultType.PASS.getCode().equals(approveResult)) {
            data.setStatus(EApproveStatus.PASS.getCode());
            userBO.refreshIdentity(data.getApplyUser(), data.getRealName(),
                data.getIdKind(), data.getIdNo(), data.getIdFace(),
                data.getIdOppo(), data.getIdHold());

        } else {
            data.setStatus(EApproveStatus.REFUSE.getCode());
        }
        data.setApproveUser(approveUser);
        data.setRemark(remark);
        userIdAuthBO.refreshApprove(data);

    }

    @Override
    public UserIdAuth getUserIdAuth(Long id) {
        return userIdAuthBO.getUserIdAuth(id);
    }

    @Override
    public List<UserIdAuth> getUserIdAuthByUserId(String userId) {
        UserIdAuth condition = new UserIdAuth();
        condition.setApplyUser(userId);
        return userIdAuthBO.queryUserIdAuthList(condition);
    }

    @Override
    public Paginable<UserIdAuth> queryUserIdAuthPage(int start, int limit,
            UserIdAuth condition) {
        return userIdAuthBO.getPaginable(start, limit, condition);
    }

}
