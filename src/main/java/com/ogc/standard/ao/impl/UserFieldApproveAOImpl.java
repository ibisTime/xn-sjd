/**
 * @Title UserFieldApproveAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:23:48 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IUserFieldApproveAO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserFieldApproveBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserFieldApprove;
import com.ogc.standard.enums.EApplyType;
import com.ogc.standard.enums.EApproveStatus;
import com.ogc.standard.enums.EResultType;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午2:23:48 
 * @history:
 */
@Service
public class UserFieldApproveAOImpl implements IUserFieldApproveAO {

    @Autowired
    private IUserFieldApproveBO userFieldApproveBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    public void apply(String userId, String idHold, String field,
            String captcha, String type) {
        if (EApplyType.EMAIL.getCode().equals(type)) {
            smsOutBO.checkCaptcha(field, captcha, "805131");
        } else {
            smsOutBO.checkCaptcha(field, captcha, "805130");
        }
        UserFieldApprove data = new UserFieldApprove();
        data.setType(type);
        data.setApplyUser(userId);
        data.setIdHold(idHold);
        data.setField(field);
        data.setCaptcha(captcha);
        userFieldApproveBO.saveApply(data);
    }

    @Override
    public void approve(Long id, String approveResult, String approveUser,
            String remark) {
        UserFieldApprove data = userFieldApproveBO.getUserFieldApprove(id);
        if (EResultType.PASS.getCode().equals(approveResult)) {
            data.setStatus(EApproveStatus.PASS.getCode());
            if (EApplyType.MOBILE.getCode().equals(data.getType())) {
                userBO.refreshMobile(data.getApplyUser(), data.getField());
            } else {
                userBO.refreshEmail(data.getApplyUser(), data.getField());
            }
        } else {
            data.setStatus(EApproveStatus.REFUSE.getCode());
        }
        data.setApproveUser(approveUser);
        data.setRemark(remark);
        userFieldApproveBO.refreshApprove(data);
    }

    @Override
    public UserFieldApprove getUserFieldApprove(Long id) {
        return userFieldApproveBO.getUserFieldApprove(id);
    }

    @Override
    public Paginable<UserFieldApprove> queryUserFieldApprovePage(int start,
            int limit, UserFieldApprove condition) {
        return userFieldApproveBO.getPaginable(start, limit, condition);
    }

}
